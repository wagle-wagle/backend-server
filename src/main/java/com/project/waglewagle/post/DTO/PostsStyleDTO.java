package com.project.waglewagle.post.DTO;

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

    private Integer shape;

    private Integer sortCode;

    private Integer fontSize;

    private Integer fontColorCode;

    public PostStyle toEntity(){
        return PostStyle.builder()
                .fontColorCode(this.fontColorCode)
                .shape(this.shape)
                .sortCode(this.sortCode)
                .fontColorCode(this.fontColorCode)
                .build();
    }

    public PostsStyleDTO(PostStyle entity){
        this.id = entity.getId();
        this.fontColorCode = entity.getFontColorCode();
        this.shape = entity.getShape();
        this.sortCode = entity.getSortCode();
        this.fontSize = entity.getFontSize();
    }
}
