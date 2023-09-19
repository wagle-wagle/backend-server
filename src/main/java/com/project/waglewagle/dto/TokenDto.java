package com.project.waglewagle.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private Long userId;
    private String accessToken;
    //private String refreshToken;
}
