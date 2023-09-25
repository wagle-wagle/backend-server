package com.project.waglewagle.dto.user;


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
    private Long broadId;


    public static UserInfoResponse of(Users user){
        try{
        return UserInfoResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .memberType(user.getMemberType().toString())
                .broadId(user.getBroad().getId())
                .build();
        }
        catch (NullPointerException e){
            return UserInfoResponse.builder()
                    .userId(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .memberType(user.getMemberType().toString())
                    .build();
        }

    }
}
