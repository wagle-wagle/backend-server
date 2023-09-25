package com.project.waglewagle.external.social.dto;

import lombok.Getter;
import lombok.Setter;

public class NaverUserInfoDto {

    private String resultcode;
    private String message;

    @Getter
    @Setter
    public static class Response{

        private String id; // 동일인 식별 정보
        private String nickname; // 별명
        private String name;    // 이름
        private String email;   // 메일

    }


}
