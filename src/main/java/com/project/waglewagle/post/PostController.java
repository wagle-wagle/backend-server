package com.project.waglewagle.post;

import com.project.waglewagle.post.DTO.PostsRequest;
import com.project.waglewagle.post.DTO.PostsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class PostController {
    @Autowired
    PostsService postsService;

    @GetMapping("/posts/all/{broadId}")
    public ResponseEntity<List<PostsResponse>> getAllPosts(@RequestParam(name = "reverse", defaultValue = "False") boolean reverse,
                                                           @PathVariable("broadId") Long broadId){
        List<PostsResponse> responses = postsService.getAllPost(broadId,reverse);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/posts/detail/{postId}")
    public ResponseEntity<PostsResponse> getPosts(@PathVariable("postId") Long postId){
        PostsResponse response = postsService.getPost(postId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<String> createPosts(@RequestBody PostsRequest request){
        postsService.createPost(request);
        return new ResponseEntity<>("good",HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePosts(@PathVariable("postId") Long postId){
        postsService.deletePost(postId);
        return new ResponseEntity<>("good",HttpStatus.OK);
    }
}
