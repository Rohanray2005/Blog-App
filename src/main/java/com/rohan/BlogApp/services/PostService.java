package com.rohan.BlogApp.services;

import com.rohan.BlogApp.payload.PostDto;

import com.rohan.BlogApp.entites.Post;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPost();

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto>getPostsByUser(Integer userId);

}
