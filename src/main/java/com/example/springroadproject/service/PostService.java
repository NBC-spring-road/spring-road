package com.example.springroadproject.service;

import com.example.springroadproject.dto.*;
import com.example.springroadproject.entity.AdminPost;
import com.example.springroadproject.entity.Post;
import com.example.springroadproject.repository.AdminPostRepository;
import com.example.springroadproject.repository.PostRepository;
import com.example.springroadproject.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    //adminpostrepository추가
    private final AdminPostRepository adminPostRepository;
    public PostResponseDto createPost(PostRequestDto postRequestDto, UserDetailsImpl userDetailsImpl) {
        Post post = new Post(postRequestDto,userDetailsImpl);
        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(savePost);

        return postResponseDto;
    }

    public List<PostResponseDto> getPostList() {

        List<Post> postList = postRepository.findAll();
        List<PostResponseDto> response = new ArrayList<>();
        for(int i = 0; i< postList.size();i++){
            response.add(new PostResponseDto(postList.get(i)));
        }
        return response;
    }

    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 id 게시물이 없습니다."));
        return new PostResponseDto(post);
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto requestDto, UserDetailsImpl user) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 id 게시물이 없습니다."));
        if(!Objects.equals(post.getUser().getId(), user.getUser().getId())){
            throw new IllegalArgumentException("게시물 작성자만 수정 가능합니다");
        }
        post.update(requestDto);
    }

    public void deletePost(Long id, UserDetailsImpl user) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 id 게시물이 없습니다."));
        if(!Objects.equals(post.getUser().getId(), user.getUser().getId())){
            throw new IllegalArgumentException("게시물 작성자만 삭제 가능합니다");
        }
        postRepository.delete(post);
    }

    public AdminPostResponseDto createPostByAdmin(AdminPostRequestDto requestDto, UserDetailsImpl userDetails) {
        AdminPost adminPost=new AdminPost(requestDto,userDetails);
        AdminPost saveAdminPost = adminPostRepository.save(adminPost);

        return new AdminPostResponseDto(saveAdminPost);
    }

    public AdminPostResponseDto getNoticePost(Long id) {
        AdminPost adminPost=adminPostRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 id 공지상항은 없습니다."));
        return new AdminPostResponseDto(adminPost);
    }

    @Transactional
    public void updatePostByAdmin(PostRequestDto requestDto, Long postId) {
        Post post =postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("해당 id 게시물이 없습니다."));
        post.update(requestDto);
    }
}


