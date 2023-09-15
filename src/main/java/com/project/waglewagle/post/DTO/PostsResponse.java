package com.project.waglewagle.post.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.waglewagle.post.Post;
import com.project.waglewagle.post.PostStyle;
import lombok.Getter;

@Getter
public class PostsResponse {
    private Long id;

    private String version;

    private String message;

    private String nickName;

    private PostsStyleDTO postStyle;

    private Long createdTime;

    public PostsResponse(Post entity) {
        this.id = entity.getId();
        this.nickName = entity.getNickName();
        this.version = entity.getVersion();
        this.message = entity.getMessage();
        this.postStyle = new PostsStyleDTO(entity.getPostStyle());
        this.createdTime = entity.getCreatedTime();
    }
}
