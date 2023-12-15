package com.rohan.BlogApp.repository;

import com.rohan.BlogApp.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
