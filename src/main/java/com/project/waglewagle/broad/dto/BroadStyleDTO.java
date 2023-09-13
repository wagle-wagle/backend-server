package com.project.waglewagle.broad.dto;

import com.project.waglewagle.broad.BroadStyle;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BroadStyleDTO {
    private Long id;

    private Integer color_Code;

    private Integer backGround_Code;

    private Integer friend_Code;

    public BroadStyle toEntity(){
        return BroadStyle.builder()
                .backGround_Code(this.backGround_Code)
                .color_Code(this.color_Code)
                .friend_Code(this.friend_Code)
                .build();
    }
}
