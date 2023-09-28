package com.project.waglewagle.external.social.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverUserInfoDto {

    private String resultcode; // 결과코드
    private String message;    // 결과 메세지
    private Response response;


    @Getter
    @Setter
    public static class Response{

        private String id; // 동일인 식별 정보
        private String nickname; // 별명
        private String name;    // 이름
        private String email;   // 메일

    }


}
