package com.project.waglewagle.post;

import com.project.waglewagle.broad.Broad;
import com.project.waglewagle.broad.BroadRepository;
import com.project.waglewagle.global.error.ErrorCode;
import com.project.waglewagle.global.error.exception.EntityNotFoundException;
import com.project.waglewagle.post.DTO.PostsRequest;
import com.project.waglewagle.post.DTO.PostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService{

    private final PostsStyleRepository postsStyleRepository;

    private final PostsRepository postsRepository;

    private final BroadRepository broadRepository;
    @Override
    public List<PostsResponse> getAllPost(Long broadId, boolean reverse) {
        if(reverse){
            List<Post> posts = postsRepository.findAllByBroadId(broadId, Sort.by(Sort.Direction.DESC, "Id"));
            if(posts.isEmpty()){
                throw new EntityNotFoundException(ErrorCode.POST_NOT_EXIST);
            }
            List<PostsResponse> responses = posts.stream()
                    .map(m-> new PostsResponse(m))
                    .collect(Collectors.toList());
            return responses;
        }
        List<Post> posts = postsRepository.findAllByBroadId(broadId, Sort.by(Sort.Direction.ASC, "Id"));
        if(posts.isEmpty()){
            throw new EntityNotFoundException(ErrorCode.POST_NOT_EXIST);
        }
        List<PostsResponse> responses = posts.stream()
                .map(m-> new PostsResponse(m))
                .collect(Collectors.toList());
        return responses;
    }

    @Override
    public PostsResponse getPost(Long postId) {
        Post post = postsRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_EXIST));;
        return new PostsResponse(post);
    }

    @Override
    public void createPost(PostsRequest postsRequest) {
        PostStyle postStyle = postsStyleRepository.save(postsRequest.getPostStyle().toEntity());

        Post post = Post.builder()
                .broad(broadRepository.findById(postsRequest.getBroadId()).get())
                .message(postsRequest.getMessage())
                .version(postsRequest.getVersion())
                .postStyle(postStyle)
                .nickName(postsRequest.getNickName())
                .build();
        try {
            postsRepository.save(post);
        }
        catch (NoSuchElementException e){
            throw new EntityNotFoundException(ErrorCode.BROAD_NOT_EXIST);
        }

    }

    @Override
    public void deletePost(Long postId) {
        postsRepository.deleteById(postId);

    }
}
