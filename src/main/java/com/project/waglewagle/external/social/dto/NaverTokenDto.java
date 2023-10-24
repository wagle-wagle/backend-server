package com.project.waglewagle.external.social.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class NaverTokenDto {
    @Builder
    @Getter
    public static class Request {
        private String grant_type;
        private String client_id;
        private String client_secret;
        private String code;
        private String state;

    }
    @ToString
    @Builder @Getter
    public static class Response {
        private String token_type; // Bearer
        private String access_token;
        private String refresh_token;
        private Integer expires_in; // 접근토큰 유효기간(초)
        private String error; // 에러코드
        private String error_description; // 에러메세지



    }

}
