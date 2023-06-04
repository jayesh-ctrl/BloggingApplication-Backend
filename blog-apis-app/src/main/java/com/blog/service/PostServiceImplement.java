package com.blog.service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.repository.CategoryRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplement implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," User Id ",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," Category Id ",categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }



    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> postList = this.postRepo.findAll();
        List<PostDto> postDtoList = postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public PostDto getSinglePost(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," category Id ",categoryId));
        List<Post> postList = this.postRepo.findByCategory(category);
        List<PostDto> postDtoList = postList.stream().map((post -> this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," userId ",userId));
        List<Post> postList = this.postRepo.findByUser(user);

        List<PostDto> postDtoList = postList.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
