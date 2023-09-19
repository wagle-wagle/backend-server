package com.project.waglewagle.controller;

import com.project.waglewagle.dto.*;
import com.project.waglewagle.dto.common.ResultDto;
import com.project.waglewagle.global.config.jwt.JwtFilter;
import com.project.waglewagle.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<ResultDto> login(@Valid @RequestBody LoginRequest loginDto) {

        LoginResponse loginResponse = userService.login(loginDto.getEmail(), loginDto.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + loginResponse.getAccessToken());

        ResultDto resultDto = ResultDto.in("success", "로그인 성공적으로 완료되었습니다.");
        resultDto.setData(loginResponse);
        return new ResponseEntity<>(resultDto, httpHeaders, HttpStatus.OK);

    }



    // 회원가입
    @PostMapping("/users/signup")
    public ResponseEntity<ResultDto> signup(@Valid @RequestBody SignupRequest registerDto) {

        UserInfoResponse userInfoResponse = userService.signup(registerDto.getEmail(), registerDto.getPassword());
        TokenDto authenticate  = userService.authenticate(
                userInfoResponse.getUserid(),
                registerDto.getEmail(),
                registerDto.getPassword()
        );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + authenticate.getAccessToken());

        ResultDto resultDto = ResultDto.in("success", "회원가입 성공적으로 완료되었습니다.");

        Map<String, Long> data = new HashMap<>();
        data.put("userId", authenticate.getUserId());

        resultDto.setData(data);
        return new ResponseEntity<>(resultDto, httpHeaders, HttpStatus.OK);
    }



    @PostMapping("/users/hopae")
    public ResponseEntity<ResultDto> updateHopae(@RequestBody HopaeRequest hopaeDto){

        UserInfoResponse userResponse = userService.updateHopae(hopaeDto.getUserId(), hopaeDto.getUsername());
        ResultDto resultDto = ResultDto.in("success", "호패 등록이 성공적으로 완료되었습니다.");
        resultDto.setData(userResponse);
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }


/*
   // 이메일 중복 확인
    @GetMapping("/users/duplicate-check")
    public ResponseEntity<ResultDto> validateDuplicateMember(@RequestParam("email") String email){
        Optional<Users> user = userService.findMemberByEmail(email);
        ResultDto resultDto = ResultDto.in("success", "이메일 중복 조회 완료되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

*/


}
