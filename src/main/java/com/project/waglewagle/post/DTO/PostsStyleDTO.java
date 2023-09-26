package com.project.waglewagle.post.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.waglewagle.broad.BroadStyle;
import com.project.waglewagle.post.PostStyle;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostsStyleDTO {
    private Long id;

    @JsonProperty("shapeCode")
    private Integer shapeCode;

    @JsonProperty("sortCode")
    private Integer sortCode;

    @JsonProperty("fontSize")
    private Integer fontCode;

    @JsonProperty("fontColorCode")
    private Integer fontColorCode;

    public PostStyle toEntity(){
        return PostStyle.builder()
                .fontColorCode(this.fontColorCode)
                .shapeCode(this.shapeCode)
                .sortCode(this.sortCode)
                .fontCode(this.fontCode)
                .build();
    }

    public PostsStyleDTO(PostStyle entity){
        this.id = entity.getId();
        this.fontColorCode = entity.getFontColorCode();
        this.shapeCode = entity.getShapeCode();
        this.sortCode = entity.getSortCode();
        this.fontCode = entity.getFontCode();
    }
}
