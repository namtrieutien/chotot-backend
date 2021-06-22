package com.sunny.Sunny.service.Impl;

import com.sunny.Sunny.entity.Category;
import com.sunny.Sunny.model.request.CreateNewCategory;
import com.sunny.Sunny.exception.ResourceNotFoundException;
import com.sunny.Sunny.repository.CategoryRepository;
import com.sunny.Sunny.repository.PostRepository;
import com.sunny.Sunny.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public Category createNewCategory(CreateNewCategory createNewCategory) {
        Category category = new Category(createNewCategory.getName(), createNewCategory.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Delete with category id: " + id + " not success"));
        categoryRepository.delete(category);
        return category;
    }

}
