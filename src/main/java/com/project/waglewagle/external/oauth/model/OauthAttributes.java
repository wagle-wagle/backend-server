package com.project.waglewagle.external.oauth.model;

import com.project.waglewagle.entity.MemberType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OauthAttributes {
    private String userName;
    private String email;
    private String password;
    private MemberType memberType;
}
