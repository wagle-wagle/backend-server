package com.project.waglewagle.external.social.service;

import com.project.waglewagle.dto.user.LoginResponse;
import com.project.waglewagle.dto.TokenDto;
import com.project.waglewagle.dto.user.UserInfoResponse;
import com.project.waglewagle.entity.MemberType;
import com.project.waglewagle.entity.Users;
import com.project.waglewagle.external.social.client.KakaoTokenClient;
import com.project.waglewagle.external.social.client.KakaoUserInfoClient;
import com.project.waglewagle.external.social.dto.KakaoTokenDto;
import com.project.waglewagle.external.social.dto.KakaoUserInfoDto;
import com.project.waglewagle.external.oauth.model.OauthAttributes;
import com.project.waglewagle.global.config.jwt.JwtFilter;
import com.project.waglewagle.global.config.jwt.TokenService;
import com.project.waglewagle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final UserService userService;
    private final TokenService tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @Value("${kakao.client.id}")
    private String clientId;
    @Value("${kakao.client.secret}")
    private String clientSecret;


    // 토큰 발급
    public String getAccessToken(String code) {

        String contentType = "application/x-www-form-urlencoded;charset=utf-8";

        KakaoTokenDto.Request kakaoTokenRequest = KakaoTokenDto.Request.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .grant_type("authorization_code")
                .code(code)
                .redirect_uri("http://localhost:8080/oauth/kakao/callback")
                .build();

        KakaoTokenDto.Response kakaoTokenResponse
                = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequest);

        log.info("===================== 카카오 엑세스 토큰 발급 getAccessToken = " + kakaoTokenResponse.getAccess_token() + "===========================");
        return kakaoTokenResponse.getAccess_token();
    }



    // 사용자 정보 가져오기
    public OauthAttributes getUserInfo(String accessToken) {

        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        KakaoUserInfoDto kakaoUserInfoResponse
                = kakaoUserInfoClient.getKakaoUserInfo(contentType, "Bearer " + accessToken);

        KakaoUserInfoDto.KakaoAccount kakaoAccount = kakaoUserInfoResponse.getKakaoAccount();
        String email = kakaoAccount.getEmail();
        String id = kakaoUserInfoResponse.getId();

        log.info("===================== 카카오 사용자 정보 가져오기 getUserInfo = " + email + "/" + id + "===========================");

        return OauthAttributes.builder()
                .username(kakaoAccount.getProfile().getNickname())
                .email(!StringUtils.hasText(email) ? kakaoUserInfoResponse.getId() : email) // 이메일 존재하지 않은경우 회원번호
                .password(kakaoUserInfoResponse.getId()+"_"+MemberType.KAKAO)
                .memberType(MemberType.KAKAO)
                .build();
    }



    public LoginResponse emailExist(OauthAttributes kakaoUserInfo) {

        Optional<Users> findUser = userService.findMemberByEmail(kakaoUserInfo.getEmail());

        if (findUser.isEmpty()) {
            log.info("===================== 카카오 신규회원 가입 =====================");
            UserInfoResponse users = userService.signup(kakaoUserInfo);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(kakaoUserInfo.getEmail(), kakaoUserInfo.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TokenDto token = tokenProvider.createToken(authentication, users.getUserId());
            log.info("KAKAO 로그인 token 발급 (신규) {} ", token.getAccessToken());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

            LoginResponse loginResponse = LoginResponse.builder()
                    .userId(users.getUserId())
                    .username(users.getUserName())
                    .isExistHopae(users.getUserName() == null? false : true)
                    .accessToken(token.getAccessToken())
                    .build();

            return loginResponse;

        } else {
            log.info("===================== 카카오 로그인  =====================");
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(kakaoUserInfo.getEmail(), kakaoUserInfo.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TokenDto token = tokenProvider.createToken(authentication, findUser.get().getId());
            log.info("KAKAO 로그인 token 발급 (기존) {} ", token.getAccessToken());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());


            LoginResponse loginResponse = LoginResponse.builder()
                    .userId(findUser.get().getId())
                    .username(findUser.get().getUsername())
                    .isExistHopae(findUser.get().getUsername() == null ? false : true)
                    .accessToken(token.getAccessToken())
                    .build();

            return loginResponse;
        }
    }

}
