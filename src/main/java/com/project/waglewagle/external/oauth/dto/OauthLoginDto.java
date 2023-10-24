package com.project.waglewagle.external.oauth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class OauthLoginDto {

    @Getter @Setter
    public static class Request{
        private String memberType;
        private String code;
        private String state;
    }

    @Getter @Setter
    @Builder
    public static class Response{
        private String accessToken;
    }
}
