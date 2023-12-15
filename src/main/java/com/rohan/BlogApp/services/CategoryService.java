package com.rohan.BlogApp.services;

import com.rohan.BlogApp.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    public void deleteCategory(Integer categoryId);

    public CategoryDto getCategory(Integer categoryId);

    public List<CategoryDto> getCategories();
}
