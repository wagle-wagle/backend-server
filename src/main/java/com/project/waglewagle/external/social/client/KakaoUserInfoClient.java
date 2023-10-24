package com.project.waglewagle.external.social.client;

import com.project.waglewagle.external.social.dto.KakaoUserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://kapi.kakao.com", name = "kakaoUserInfoClient")
public interface KakaoUserInfoClient {
    // 사용자 정보 가져오기
    @GetMapping(value = "/v2/user/me", consumes = "application/json")
    KakaoUserInfoDto getKakaoUserInfo(@RequestHeader("Content-type") String contentType,
                                      @RequestHeader("Authorization") String accessToken);
}
