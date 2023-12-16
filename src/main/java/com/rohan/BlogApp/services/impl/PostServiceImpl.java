package com.rohan.BlogApp.services.impl;

import com.rohan.BlogApp.entites.Category;
import com.rohan.BlogApp.entites.Post;
import com.rohan.BlogApp.entites.User;
import com.rohan.BlogApp.exceptions.ResourceNotFoundException;
import com.rohan.BlogApp.payload.PostDto;
import com.rohan.BlogApp.repository.CategoryRepo;
import com.rohan.BlogApp.repository.PostRepo;
import com.rohan.BlogApp.repository.UserRepo;
import com.rohan.BlogApp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId",userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);

        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post>posts = this.postRepo.findAll();
        System.out.println(posts.size());
        List<PostDto>postdtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postdtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        List<Post>posts = this.postRepo.findByCategory(category);
        List<PostDto>postdtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postdtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));

        List<Post>posts = this.postRepo.findByUser(user);
        List<PostDto>postdtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postdtos;
    }
}
// Pagination continued...
//    @Override
//    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
//
//        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
//        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
//
//        Page<Post> pagePost = this.postRepo.findAll(p);
//
//        List<Post> allPosts = pagePost.getContent();
//
//        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
//                .collect(Collectors.toList());
//
//        PostResponse postResponse = new PostResponse();
//
//        postResponse.setContent(postDtos);
//        postResponse.setPageNumber(pagePost.getNumber());
//        postResponse.setPageSize(pagePost.getSize());
//        postResponse.setTotalElements(pagePost.getTotalElements());
//
//        postResponse.setTotalPages(pagePost.getTotalPages());
//        postResponse.setLastPage(pagePost.isLast());
//
//        return postResponse;
//    }
