package com.rohan.BlogApp.controllers;

import com.rohan.BlogApp.entites.Comment;
import com.rohan.BlogApp.payload.ApiResponse;
import com.rohan.BlogApp.payload.CommentDto;
import com.rohan.BlogApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/Comments")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto createComment = this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!",true),HttpStatus.OK);
    }
}
