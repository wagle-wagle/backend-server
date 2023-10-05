package com.project.waglewagle.post.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.waglewagle.broad.Broad;
import com.project.waglewagle.post.PostStyle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostsRequest {
    @NotNull
    private Long broadId;

    @NotNull
    private String version;

    @NotNull
    private String message;

    @NotNull
    @JsonProperty("postStyle")
    private PostsStyleDTO postStyle;

    @NotNull
    @JsonProperty("nickName")
    private String nickName;
}
