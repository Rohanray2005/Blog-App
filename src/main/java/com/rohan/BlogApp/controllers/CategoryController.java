package com.rohan.BlogApp.controllers;

import com.rohan.BlogApp.entites.Category;
import com.rohan.BlogApp.payload.CategoryDto;
import com.rohan.BlogApp.payload.UserDto;
import com.rohan.BlogApp.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                     @PathVariable("categoryId") Integer cid){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,cid);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?>deleteUser(@PathVariable("categoryId")Integer cid){
        this.categoryService.deleteCategory(cid);
        return ResponseEntity.ok(Map.of("message","User deleted successfully"));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>getUser(@PathVariable("categoryId")Integer cid){
        return ResponseEntity.ok(this.categoryService.getCategory(cid));
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>getAllUsers(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

}
