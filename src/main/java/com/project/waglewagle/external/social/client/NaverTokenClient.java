package com.project.waglewagle.external.social.client;
import com.project.waglewagle.external.social.dto.KakaoTokenDto;
import com.project.waglewagle.external.social.dto.NaverTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://nid.naver.com/oauth2.0", name = "naverTokenClient")
public interface NaverTokenClient {
    // 네이버 토큰 발급 요청
    @PostMapping(value = "/token", consumes = "application/json")
    NaverTokenDto.Response requestNaverToken(@RequestHeader("Content-Type") String contentType,
                                             @SpringQueryMap NaverTokenDto.Request request);

}
