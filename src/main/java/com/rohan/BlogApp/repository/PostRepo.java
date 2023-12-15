package com.rohan.BlogApp.repository;

import com.rohan.BlogApp.entites.Category;
import com.rohan.BlogApp.entites.Post;
import com.rohan.BlogApp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post>findByUser(User user);
    List<Post>findByCategory(Category category);
}
