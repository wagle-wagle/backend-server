package com.project.waglewagle.controller;

import com.project.waglewagle.dto.*;
import com.project.waglewagle.dto.user.UpdatePasswordRequest;
import com.project.waglewagle.dto.user.*;
import com.project.waglewagle.global.config.jwt.TokenService;
import com.project.waglewagle.global.config.security.PrincipalDetail;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class UserController {

    public static final String TOKEN_PREFIX = "Bearer ";
    private final TokenService tokenService;
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
        UserInfoResponse userResponse = userService.updateHopae(hopaeDto.getUserId(), hopaeDto.getUserName());
        return ApiResponse.createSuccess("호패 등록이 성공적으로 완료되었습니다.", HttpStatus.OK, userResponse);
    }


    @GetMapping("/users/duplicate-check")
    public CommonResponse<Void> validateDuplicateMember(@RequestParam("email") String email){
        Optional<Users> user = userService.findMemberByEmail(email);
        return ApiResponse.createSuccess("사용 가능한 이메일 입니다.", HttpStatus.OK, null);
    }


    /**
     * 임시 비밀번호 발급
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/users/password/temporary-email")
    public CommonResponse<Boolean> sendEmailTemporaryPassword(@RequestBody SendEmailRequest sendEmailRequestDto) throws Exception {
        Users users = userService.sendEmailTemporaryPassword(sendEmailRequestDto.getEmail());
        return ApiResponse.createSuccess("해당 이메일로 임시 비밀번호 발급되었습니다.", HttpStatus.OK, null);
    }


    @GetMapping("/users/validation")
    public CommonResponse<Void> verifyEmail(@RequestParam("email") String email){
        boolean isExistEmail = userService.verifyEmail(email);
        return ApiResponse.createSuccess("성공적으로 이메일 조회 완료되었습니다. ", HttpStatus.OK, null);
    }


    @PostMapping("/users/password/validation")
    @PreAuthorize("isAuthenticated()")
    public CommonResponse<Boolean> verifyPassword(
            @RequestHeader("Authorization") String accessToken,
            @AuthenticationPrincipal PrincipalDetail user, @RequestBody UpdatePasswordRequest updatePasswordRequestDto){
        //Long jwtUserId = tokenService.getUserId(accessToken.substring(TOKEN_PREFIX.length()));
        //boolean result = userService.verifyPassword(jwtUserId, updatePasswordRequestDto.getPassword());
        boolean result = userService.verifyPassword(user.getUser().getId(), updatePasswordRequestDto.getPassword());
        return ApiResponse.createSuccess("비밀번호 정상적으로 확인되었습니다.", HttpStatus.OK, null);
    }


    @PutMapping("/users/password/change")
    @PreAuthorize("isAuthenticated()")
    public CommonResponse<Void> updatePassword(
            @RequestHeader("Authorization") String accessToken,
            @AuthenticationPrincipal PrincipalDetail user, @RequestBody UpdatePasswordRequest updatePasswordRequestDto){
        //Long jwtUserId = tokenService.getUserId(accessToken.substring(TOKEN_PREFIX.length()));
        //userService.updatePassword(jwtUserId, updatePasswordRequestDto.getPassword());
        userService.updatePassword(user.getUser().getId(), updatePasswordRequestDto.getPassword());
        return ApiResponse.createSuccess("비밀번호 변경 완료 되었습니다.", HttpStatus.OK, null);
    }


    @DeleteMapping("/users/{userId}")
    @PreAuthorize("isAuthenticated()")
    public CommonResponse<Void> deleteUserAccount(
            @RequestHeader("Authorization") String accessToken,
            @AuthenticationPrincipal PrincipalDetail user){
        //Long jwtUserId = tokenService.getUserId(accessToken.substring(TOKEN_PREFIX.length()));
        //Users users = userService.deleteUser(jwtUserId);
        //result.put("userId", jwtUserId);
        Users users = userService.deleteUser(user.getUser().getId());
        Map<String, Long> result = new HashMap<>();
        result.put("userId", users.getId());
        return ApiResponse.createSuccess("회원탈퇴 정상적으로 완료되었습니다.", HttpStatus.OK, result);
    }



}
