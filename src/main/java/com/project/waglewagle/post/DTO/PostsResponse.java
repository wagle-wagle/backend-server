package com.project.waglewagle.post.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.waglewagle.post.Post;
import com.project.waglewagle.post.PostStyle;

public class PostsResponse {
    private Long id;

    private String version;

    private String message;

    private PostsStyleDTO postStyle;

    public PostsResponse(Post entity) {
        this.id = entity.getId();
        this.version = entity.getVersion();
        this.message = entity.getMessage();
        this.postStyle = new PostsStyleDTO(entity.getPostStyle());
    }
}
