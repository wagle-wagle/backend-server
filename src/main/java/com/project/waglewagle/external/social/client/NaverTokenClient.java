package com.project.waglewagle.external.social.client;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "https://nid.naver.com/oauth2.0", name = "naverTokenClient")
public interface NaverTokenClient {
    // 네이버 토큰 발급 요청
}
