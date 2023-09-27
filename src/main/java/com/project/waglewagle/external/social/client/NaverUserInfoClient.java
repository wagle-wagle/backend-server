package com.project.waglewagle.external.social.client;

import com.project.waglewagle.external.social.dto.NaverUserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(url = "https://openapi.naver.com", name = "naverUserInfoClient")
public interface NaverUserInfoClient {
    // 프로필 정보 조회
    @PostMapping("/v1/nid/me")
    NaverUserInfoDto getNaverUserInfo(@RequestHeader("Content-type") String contentType,
                                      @RequestHeader("Authorization") String accessToken);

}
