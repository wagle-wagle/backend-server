package com.project.waglewagle.dto.user;

import lombok.*;

@Getter
public class LoginResponse {
    private Long userId;
    private String userName;
    private Boolean isExistHopae;
    private String accessToken;


    @Builder
    public LoginResponse(Long userId, String accessToken, String userName, boolean isExistHopae){
        this.userId = userId;
        this.userName = userName;
        this.isExistHopae = isExistHopae;
        this.accessToken = accessToken;
    }


}
