package com.blog.controller;

import com.blog.entities.Post;
import com.blog.payloads.APIResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.service.PostService;

import jakarta.persistence.criteria.CriteriaBuilder.In;
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

    // get All posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = "postId", required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId) {
        PostDto post = this.postService.getSinglePost(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public APIResponse deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new APIResponse("Post is successfully Deleted", true);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatePost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

}
