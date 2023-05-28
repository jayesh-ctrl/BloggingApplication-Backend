package com.blog.service;

import com.blog.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    // create-Category
    CategoryDto createCategory(CategoryDto categoryDto);

    // update-Category
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    //delete-Category
    void deleteCategory(Integer categoryId);

    // get by Id
    CategoryDto getSingleCategory(Integer categoryId);

    // get all the category
    List<CategoryDto> getAllCategories();
}
