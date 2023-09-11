package com.project.waglewagle.external.oauth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class OauthLoginDto {

    @Getter @Setter
    public static class Request{
        private String memberType;
        private String code;
    }

    @Getter @Setter
    @Builder
    public static class Response{
        private String accessToken;
    }
}
