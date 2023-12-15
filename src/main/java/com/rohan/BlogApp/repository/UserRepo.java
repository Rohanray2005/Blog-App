package com.rohan.BlogApp.repository;

import com.rohan.BlogApp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
