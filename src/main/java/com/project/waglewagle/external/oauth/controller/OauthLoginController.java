package com.project.waglewagle.external.oauth.controller;


import com.project.waglewagle.dto.user.LoginResponse;
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
    //private final NaverLoginService naverLoginService;


    @PostMapping("/kakao")
    public CommonResponse<LoginResponse> kakaoSocialLogin(@RequestParam("code") String code){
        String accessToken = kakaoLoginService.getAccessToken(code);
        OauthAttributes kakaoUserInfo = kakaoLoginService.getUserInfo(accessToken);
        LoginResponse loginResponse = kakaoLoginService.emailExist(kakaoUserInfo);
        return ApiResponse.createSuccess("카카오 social 로그인 성공적으로 완료되었습니다. ", HttpStatus.OK, loginResponse);
    }

}