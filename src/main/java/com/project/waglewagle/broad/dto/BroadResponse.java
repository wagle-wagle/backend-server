package com.project.waglewagle.broad.dto;

import com.project.waglewagle.broad.Broad;
import com.project.waglewagle.broad.BroadStyle;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class BroadResponse {
    private Long id;

    private String version;

    private String title;

    private BroadStyleDTO broadStyle;

    private String url;

    private Long createdTime;

    private Long updatedTime;

    public BroadResponse(Broad entity) {
        this.id = entity.getId();
        this.version = entity.getVersion();
        this.title = entity.getTitle();
        this.broadStyle = BroadStyleDTO.builder()
                .id(entity.getBroadStyle().getId())
                .colorCode(entity.getBroadStyle().getColorCode())
                .backGroundCode(entity.getBroadStyle().getBackGroundCode())
                .friendCode(entity.getBroadStyle().getFriendCode())
                .build();
        this.url = entity.getUrl();
        this.createdTime = entity.getCreatedTime();
        this.updatedTime = entity.getUpdatedTime();
    }
}
