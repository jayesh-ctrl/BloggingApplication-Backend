package com.blog.service;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get All Posts();
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);

    // get Single Post();
    PostDto getSinglePost(Integer postId);

    // get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all post by user
    List<PostDto> getPostsByUser(Integer userId);

    // Search Posts;
    List<PostDto> searchPosts(String keyword);

}
