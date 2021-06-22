package com.sunny.Sunny.controller;

import com.sunny.Sunny.entity.Category;
import com.sunny.Sunny.exception.ResourceNotFoundException;
import com.sunny.Sunny.model.request.CreateNewCategory;
import com.sunny.Sunny.model.mapper.CategoryMapper;
import com.sunny.Sunny.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create-new-category")
    public ResponseEntity<?> createNewPost(@Valid @RequestBody CreateNewCategory createNewCategory) {
        Category category = categoryService.createNewCategory(createNewCategory);
        return ResponseEntity.ok(CategoryMapper.toCategoryDto(category));
    }

    @DeleteMapping("/delete-category-by-id")
    public ResponseEntity<?> deleteCategoryById(@RequestParam("id") int id) {
        Category category = categoryService.deleteCategoryById(id);
        if (category == null) {
            throw new ResourceNotFoundException("Category with id: " + id + " not found!");
        }
        return ResponseEntity.ok(CategoryMapper.toCategoryDto(category));
    }
}
