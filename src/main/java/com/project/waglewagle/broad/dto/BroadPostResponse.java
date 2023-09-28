package com.project.waglewagle.broad.dto;

import lombok.Getter;

@Getter
public class BroadPostResponse {
    private Long broadId;

    public BroadPostResponse(Long broadId) {
        this.broadId = broadId;
    }
}
