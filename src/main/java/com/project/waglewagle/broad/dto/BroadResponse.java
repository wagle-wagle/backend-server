package com.project.waglewagle.broad.dto;

import com.project.waglewagle.broad.Broad;
import com.project.waglewagle.broad.BroadStyle;
import jakarta.persistence.*;

public class BroadResponse {
    private Long id;

    private String version;

    private String title;

    private BroadStyleDTO broadStyle;

    private String url;

    public BroadResponse(Broad entity) {
        this.id = entity.getId();
        this.version = entity.getVersion();
        this.title = entity.getTitle();
        this.broadStyle = BroadStyleDTO.builder()
                .id(entity.getBroadStyle().getId())
                .color_Code(entity.getBroadStyle().getColor_Code())
                .backGround_Code(entity.getBroadStyle().getBackGround_Code())
                .friend_Code(entity.getBroadStyle().getFriend_Code())
                .build();
        this.url = entity.getUrl();
    }
}
