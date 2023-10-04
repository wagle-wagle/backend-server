package com.project.waglewagle.post;

import com.project.waglewagle.global.util.ApiResponse;
import com.project.waglewagle.global.util.CommonResponse;
import com.project.waglewagle.post.DTO.PostsRequest;
import com.project.waglewagle.post.DTO.PostsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostsService postsService;

    @GetMapping("/posts/all/{broadId}")
    public CommonResponse<List<PostsResponse>> getAllPosts(@RequestParam(name = "reverse", defaultValue = "False") boolean reverse,
                                      @PathVariable("broadId") Long broadId){
        List<PostsResponse> responses = postsService.getAllPost(broadId,reverse);
        return ApiResponse.createSuccess("기와를 성공적으로 불러왔습니다.", HttpStatus.OK,responses);
    }

    @GetMapping("/posts/detail/{postId}")
    public CommonResponse<PostsResponse> getPosts(@PathVariable("postId") Long postId){
        PostsResponse response = postsService.getPost(postId);
        return ApiResponse.createSuccess("기와 상세정보를 성공적으로 불러왔습니다.", HttpStatus.OK,response);
    }

    @PostMapping("/posts")
    public CommonResponse<Void> createPosts(@Valid @RequestBody PostsRequest request){
        postsService.createPost(request);
        return ApiResponse.createSuccess("기와를 성공적으로 등록했습니다.", HttpStatus.CREATED,null);
    }

    @DeleteMapping("/posts/{postId}")
    public CommonResponse<Void> deletePosts(@PathVariable("postId") Long postId){
        postsService.deletePost(postId);
        return ApiResponse.createSuccess("기와를 성공적으로 삭제했습니다.", HttpStatus.NO_CONTENT,null);
    }

    @PatchMapping("/posts/{postId}")
    public CommonResponse readPost(@PathVariable("postId") Long postId){
        postsService.readPost(postId);
        return ApiResponse.createSuccess("기와 읽음처리 완료.", HttpStatus.CREATED,null);
    }
}
