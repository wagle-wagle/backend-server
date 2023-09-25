package com.project.waglewagle.external.social.client;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(url = "https://openapi.naver.com", name = "naverUserInfoClient")
public interface NaverUserInfoClient {

}
