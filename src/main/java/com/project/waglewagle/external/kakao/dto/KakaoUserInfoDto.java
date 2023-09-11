package com.project.waglewagle.external.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoUserInfoDto {

    private String id;    // 회원번호
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @Setter
    public static class KakaoAccount {

        private String email;
        private Profile profile;

        @Getter @Setter
        public static class Profile{
            private String nickname;
        }

    }

}
