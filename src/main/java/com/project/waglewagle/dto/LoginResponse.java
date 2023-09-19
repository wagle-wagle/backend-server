package com.project.waglewagle.dto;

import lombok.*;

@Getter
public class LoginResponse {
    private Long userId;
    private String username;
    private Boolean isExistHopae;
    private String accessToken;


    @Builder
    public LoginResponse(Long userId, String accessToken, String username, boolean isExistHopae){
        this.userId = userId;
        this.username = username;
        this.isExistHopae = isExistHopae;
        this.accessToken = accessToken;
    }


}
