package com.blog.controller;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.service.PostService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {

        System.out.print("Content " + postDto.getContent());
        PostDto createdPost = this.postService.createPost(postDto,categoryId,userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> postDtoList = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    //get post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtoList = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

}
