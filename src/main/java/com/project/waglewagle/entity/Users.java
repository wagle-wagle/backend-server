package com.project.waglewagle.entity;

import com.project.waglewagle.broad.Broad;
import com.project.waglewagle.entity.common.BaseTimeEntity;
import com.project.waglewagle.post.PostStyle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;

    @Column
    private String userName;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "broad_id")
    private Broad broad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType memberType;

    @Column
    private String roles;

    @Column
    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public void updateUsername(String hopae){
        this.userName = hopae;
    }

    public void createBroad(Broad broad){
        this.broad = broad;
    }

}
