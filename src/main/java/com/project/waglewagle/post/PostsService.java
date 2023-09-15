package com.project.waglewagle.post;

import com.project.waglewagle.post.DTO.PostsRequest;
import com.project.waglewagle.post.DTO.PostsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostsService {
    List<PostsResponse> getAllPost(Long broadId,boolean reverse);

    PostsResponse getPost(Long postId);

    void createPost(PostsRequest postsRequest);

    void deletePost(Long postId);


}
