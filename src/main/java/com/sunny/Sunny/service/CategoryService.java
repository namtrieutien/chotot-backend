package com.sunny.Sunny.service;

import com.sunny.Sunny.entity.Category;
import com.sunny.Sunny.model.request.CreateNewCategory;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    public Category createNewCategory(CreateNewCategory createNewCategory);
    public Category deleteCategoryById(Integer id);
}
