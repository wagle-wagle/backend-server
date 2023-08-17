package com.project.waglewagle.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Builder
public class Users {
    @Id
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String memberType;

    @Column
    private String roles;

    @Column
    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }


}
