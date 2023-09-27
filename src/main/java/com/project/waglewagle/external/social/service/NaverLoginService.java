package com.project.waglewagle.external.social.service;
import com.project.waglewagle.dto.TokenDto;
import com.project.waglewagle.dto.user.LoginResponse;
import com.project.waglewagle.dto.user.UserInfoResponse;
import com.project.waglewagle.entity.MemberType;
import com.project.waglewagle.entity.Users;
import com.project.waglewagle.external.oauth.model.OauthAttributes;
import com.project.waglewagle.external.social.client.KakaoTokenClient;
import com.project.waglewagle.external.social.client.KakaoUserInfoClient;
import com.project.waglewagle.external.social.client.NaverTokenClient;
import com.project.waglewagle.external.social.client.NaverUserInfoClient;
import com.project.waglewagle.external.social.dto.KakaoUserInfoDto;
import com.project.waglewagle.external.social.dto.NaverTokenDto;
import com.project.waglewagle.external.social.dto.NaverUserInfoDto;
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

import java.lang.reflect.Member;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverLoginService {

    private final NaverTokenClient naverTokenClient;
    private final NaverUserInfoClient naverUserInfoClient;
    private final UserService userService;
    private final TokenService tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Value("${naver.client.id}")
    private String clientId;
    @Value("${naver.client.secret}")
    private String clientSecret;


    // 토큰발급
    public String getAccessToken(String code, String state){

        String contentType = "application/x-www-form-urlencoded;charset=utf-8";

        NaverTokenDto.Request naverTokenRequest = NaverTokenDto.Request.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .grant_type("authorization_code") // 발급
                .code(code)
                .state(state)
                .build();

        NaverTokenDto.Response naverTokenResponse
                = naverTokenClient.requestNaverToken(contentType,naverTokenRequest);
        log.info("===================== 네이버 엑세스 토큰 발급 getAccessToken = " + naverTokenResponse.getAccess_token() + "===========================");
        return naverTokenResponse.getAccess_token();
    }

    // 프로필 정보 조회
    public OauthAttributes getUserInfo(String accessToken) {

        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        NaverUserInfoDto naverUserInfoResponse
                = naverUserInfoClient.getNaverUserInfo(contentType, "Bearer " + accessToken);

        log.info("========= 네이버 프로필 정보조회 결과코드 : " + naverUserInfoResponse.getResultcode());
        log.info("========= 네이버 프로필 정보조회 결과메세지 : " + naverUserInfoResponse.getMessage());

        NaverUserInfoDto.Response response = naverUserInfoResponse.getResponse();
        String id = response.getId(); // 동일인 식별정보
        String nickname = response.getNickname(); // 별명
        String name = response.getName(); // 이름
        String email = response.getEmail(); // 메일
        log.info("===================== 네이버 프로필 정보 조회 getUserInfo = " + id + "/" + nickname + "/" + "===========================");

        return OauthAttributes.builder()
                .userName(nickname)
                .email(email)
                .password(id + "_" + MemberType.NAVER)
                .memberType(MemberType.NAVER)
                .build();
    }

    public LoginResponse emailExist(OauthAttributes naverUserInfo){

        Optional<Users> findUser = userService.findMemberByEmail(naverUserInfo.getEmail());

        if (findUser.isEmpty()) {
            log.info("===================== 네이버 신규회원 가입 =====================");
            UserInfoResponse users = userService.signup(naverUserInfo);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(naverUserInfo.getEmail(), naverUserInfo.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TokenDto token = tokenProvider.createToken(authentication, users.getUserId());
            log.info("NAVER 로그인 token 발급 (신규) {} ", token.getAccessToken());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

            LoginResponse loginResponse = LoginResponse.builder()
                    .userId(users.getUserId())
                    .userName(users.getUserName())
                    .isExistHopae(users.getUserName() == null? false : true)
                    .accessToken(token.getAccessToken())
                    .build();

            return loginResponse;

        } else {
            log.info("===================== 네이버 로그인  =====================");
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(naverUserInfo.getEmail(), naverUserInfo.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TokenDto token = tokenProvider.createToken(authentication, findUser.get().getId());
            log.info("NAVER 로그인 token 발급 (기존) {} ", token.getAccessToken());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());


            LoginResponse loginResponse = LoginResponse.builder()
                    .userId(findUser.get().getId())
                    .userName(findUser.get().getUserName())
                    .isExistHopae(findUser.get().getUserName() == null ? false : true)
                    .accessToken(token.getAccessToken())
                    .build();

            return loginResponse;
        }
    }
}
