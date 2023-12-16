package com.rohan.BlogApp.services.impl;

import com.rohan.BlogApp.entites.Comment;
import com.rohan.BlogApp.entites.Post;
import com.rohan.BlogApp.exceptions.ResourceNotFoundException;
import com.rohan.BlogApp.payload.CommentDto;
import com.rohan.BlogApp.repository.CommentRepo;
import com.rohan.BlogApp.repository.PostRepo;
import com.rohan.BlogApp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post id",postId));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment Id",commentId));
        this.commentRepo.delete(com);
    }
}
