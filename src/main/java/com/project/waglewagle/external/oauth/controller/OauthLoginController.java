package com.project.waglewagle.external.oauth.controller;


import com.project.waglewagle.dto.user.LoginResponse;
import com.project.waglewagle.external.oauth.dto.OauthLoginDto;
import com.project.waglewagle.external.social.service.NaverLoginService;
import com.project.waglewagle.global.util.CommonResponse;
import com.project.waglewagle.external.oauth.model.OauthAttributes;
import com.project.waglewagle.external.social.service.KakaoLoginService;
import com.project.waglewagle.global.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/login")
public class OauthLoginController {

    //private final OauthLoginService oauthLoginService;
    private final KakaoLoginService kakaoLoginService;
//    private final NaverLoginService naverLoginService;
    //private final GoogleLoginService googleLoginService;

    // 카카오 로그인
    @PostMapping("/kakao")
    public CommonResponse<LoginResponse> kakaoSocialLogin(@RequestBody OauthLoginDto.Request loginDto){
        String accessToken = kakaoLoginService.getAccessToken(loginDto.getCode());
        OauthAttributes kakaoUserInfo = kakaoLoginService.getUserInfo(accessToken);
        LoginResponse loginResponse = kakaoLoginService.emailExist(kakaoUserInfo);
        return ApiResponse.createSuccess("카카오 social 로그인 성공적으로 완료되었습니다. ", HttpStatus.OK, loginResponse);
    }


    // 네이버 로그인
//    @PostMapping("/naver")
//    public CommonResponse<LoginResponse> naverSocialLogin(@RequestBody OauthLoginDto.Request loginDto){
//
//        String accessToken = naverLoginService.getAccessToken(loginDto.getCode(), loginDto.getState());
//        OauthAttributes naverUserInfo = naverLoginService.getUserInfo(accessToken);
//        LoginResponse loginResponse = naverLoginService.emailExist(naverUserInfo);
//        return ApiResponse.createSuccess("네이버 social 로그인 성공적으로 완료되었습니다. ", HttpStatus.OK, loginResponse);
//    }


    // 구글 로그인
    @PostMapping("/google")
    public CommonResponse<LoginResponse> googleSocialLogin(@RequestBody OauthLoginDto.Request loginDto){
        return ApiResponse.createSuccess("구글 social 로그인 성공적으로 완료되었습니다. ", HttpStatus.OK, null);
    }

}