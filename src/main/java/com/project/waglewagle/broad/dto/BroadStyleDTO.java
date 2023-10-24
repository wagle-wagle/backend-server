package com.project.waglewagle.broad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.waglewagle.broad.BroadStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BroadStyleDTO {
    private Long id;

    @JsonProperty("colorCode")
    private Integer colorCode;

    @JsonProperty("backGroundCode")
    private Integer backGroundCode;

    @JsonProperty("friendCode")
    private Integer friendCode;

    public BroadStyle toEntity(){
        return BroadStyle.builder()
                .backGroundCode(this.backGroundCode)
                .colorCode(this.colorCode)
                .friendCode(this.friendCode)
                .build();
    }

    public BroadStyleDTO(BroadStyle entity) {
        this.id = entity.getId();
        this.colorCode = entity.getColorCode();
        this.backGroundCode = entity.getBackGroundCode();
        this.friendCode = entity.getFriendCode();
    }
}
