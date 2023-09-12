package com.project.waglewagle.external.oauth.controller;


import com.project.waglewagle.external.kakao.service.KakaoLoginService;
import com.project.waglewagle.external.oauth.dto.OauthLoginDto;
import com.project.waglewagle.external.oauth.model.OauthAttributes;
import com.project.waglewagle.external.oauth.service.OauthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/login")
public class OauthLoginController {

    //private final OauthLoginService oauthLoginService;
    private final KakaoLoginService kakaoLoginService;


    @PostMapping("/kakao")
    public ResponseEntity<String> kakaoLogin(@RequestParam("code") String code){
        String accessToken = kakaoLoginService.getAccessToken(code);
        OauthAttributes kakaoUserInfo = kakaoLoginService.getUserInfo(accessToken);
        kakaoLoginService.emailExist(kakaoUserInfo);
        return new ResponseEntity<>("카카오 소셜 로그인 완료!!",  HttpStatus.OK);
    }


/*    @PostMapping("/")
    public ResponseEntity<String> kakaoLogin(@RequestBody OauthLoginDto.Request oauthLoginRequsetDto){
        String accessToken = kakaoLoginService.getAccessToken(oauthLoginRequsetDto.getCode());
        OauthAttributes kakaoUserInfo = kakaoLoginService.getUserInfo(accessToken);
        kakaoLoginService.emailExist(kakaoUserInfo);
        return new ResponseEntity<>("카카오 소셜 로그인 완료!!",  HttpStatus.OK);
    }*/


}