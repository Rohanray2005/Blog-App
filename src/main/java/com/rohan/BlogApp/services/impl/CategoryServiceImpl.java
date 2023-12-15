package com.rohan.BlogApp.services.impl;

import com.rohan.BlogApp.entites.Category;
import com.rohan.BlogApp.entites.User;
import com.rohan.BlogApp.exceptions.ResourceNotFoundException;
import com.rohan.BlogApp.payload.CategoryDto;
import com.rohan.BlogApp.payload.UserDto;
import com.rohan.BlogApp.repository.CategoryRepo;
import com.rohan.BlogApp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updateCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(updateCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category>categories = this.categoryRepo.findAll();
        List<CategoryDto>categoryDtos = categories.stream().map(category->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
