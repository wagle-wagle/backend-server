package com.project.waglewagle.controller;

import com.project.waglewagle.dto.*;
import com.project.waglewagle.global.util.CommonResponse;
import com.project.waglewagle.entity.Users;
import com.project.waglewagle.global.config.jwt.JwtFilter;
import com.project.waglewagle.global.util.ApiResponse;
import com.project.waglewagle.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginDto) {

        LoginResponse loginResponse = userService.login(loginDto.getEmail(), loginDto.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + loginResponse.getAccessToken());

        return new ResponseEntity<>(
                ApiResponse.createSuccess("로그인 성공적으로 완료되었습니다.", HttpStatus.OK, loginResponse),
                httpHeaders,
                HttpStatus.OK);
    }



    // 회원가입
    @PostMapping("/users/signup")
    public ResponseEntity<CommonResponse<Object>> signup(@Valid @RequestBody SignupRequest registerDto) {

        UserInfoResponse userInfoResponse = userService.signup(registerDto.getEmail(), registerDto.getPassword());
        TokenDto authenticate  = userService.authenticate(
                userInfoResponse.getUserId(),
                registerDto.getEmail(),
                registerDto.getPassword()
        );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + authenticate.getAccessToken());

        Map<String, Long> data = new HashMap<>();
        data.put("userId", authenticate.getUserId());

        return new ResponseEntity<>(
                ApiResponse.createSuccess("회원가입 성공적으로 완료되었습니다.", HttpStatus.OK, data),
                httpHeaders,
                HttpStatus.OK);
    }




    @PostMapping("/users/hopae")
    public CommonResponse<UserInfoResponse> updateHopae(@RequestBody HopaeRequest hopaeDto){
        UserInfoResponse userResponse = userService.updateHopae(hopaeDto.getUserId(), hopaeDto.getUsername());
        return ApiResponse.createSuccess("호패 등록이 성공적으로 완료되었습니다.", HttpStatus.OK, userResponse);

    }


    @GetMapping("/users/duplicate-check")
    public CommonResponse<Void> validateDuplicateMember(@RequestParam("email") String email){
        Optional<Users> user = userService.findMemberByEmail(email);
        return ApiResponse.createSuccess("사용 가능한 이메일 입니다.", HttpStatus.OK, null);
    }




}
