package com.example.springroadproject.controller;

import com.example.springroadproject.dto.CommonResponseDto;
import com.example.springroadproject.dto.PostRequestDto;
import com.example.springroadproject.dto.PostResponseDto;
import com.example.springroadproject.security.UserDetailsImpl;
import com.example.springroadproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<CommonResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        PostResponseDto postResponseDto = postService.createPost(postRequestDto,userDetailsImpl);
        return ResponseEntity.ok().body(postResponseDto);
    }
}
