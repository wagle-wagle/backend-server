package com.project.waglewagle.dto;


import com.project.waglewagle.entity.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {

    private Long userId;
    private String email;
    private String username;
    private String memberType;


    public static UserInfoResponse of(Users user){
        return UserInfoResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .memberType(user.getMemberType().toString())
                .build();
    }

}
