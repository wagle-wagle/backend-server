package com.project.waglewagle.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HopaeRequest {
    private Long userId;
    private String username;
}
