package com.project.waglewagle.post.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.waglewagle.broad.Broad;
import com.project.waglewagle.post.PostStyle;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class PostsRequest {
    private Long broadId;

    private String version;

    private String message;

    @JsonProperty("postStyle")
    private PostsStyleDTO postStyle;

    @JsonProperty("nickName")
    private String nickName;
}
