package com.rohan.BlogApp.repository;

import com.rohan.BlogApp.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
