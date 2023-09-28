package com.project.waglewagle.dto.user;

import lombok.*;

@Getter
public class LoginResponse {
    private Long userId;
    private String userName;
    private Boolean isExistHopae;
    private String accessToken;
    private Long boardId;


    @Builder
    public LoginResponse(Long userId, Long boardId,String accessToken, String userName, boolean isExistHopae){
        this.userId = userId;
        this.userName = userName;
        this.isExistHopae = isExistHopae;
        this.accessToken = accessToken;
        this.boardId = boardId;
    }


}
