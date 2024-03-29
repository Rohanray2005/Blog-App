package com.rohan.BlogApp.controllers;

import com.rohan.BlogApp.entites.Post;
import com.rohan.BlogApp.payload.ApiResponse;
import com.rohan.BlogApp.payload.PostDto;
import com.rohan.BlogApp.services.FileService;
import com.rohan.BlogApp.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        PostDto createdPostDto = this.postService.createPost(post,userId,categoryId);
        return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>>getPostsByUserId(@PathVariable Integer userId){
        List<PostDto>postDtos = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>>getPostsByCategoryId(@PathVariable Integer categoryId){
        List<PostDto>postDtos = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>>getAllPosts(){
        List<PostDto>postDtos = this.postService.getAllPost();
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto>getPostById(Integer postId){
        PostDto postDtos = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDtos,HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfull deleted",true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,
                                             @PathVariable Integer postId){
        PostDto updatePost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto>uploadPostImage(@RequestParam("image")MultipartFile image,
                                                  @PathVariable Integer postId)throws IOException{
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path,image);
        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response)throws IOException{
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    }

}

//Pagination
//    @GetMapping("/posts")
//    public ResponseEntity<PostResponse> getAllPost(
//            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
//
//        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
//        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
//    }