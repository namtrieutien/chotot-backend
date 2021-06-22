package com.sunny.Sunny.model.mapper;

import com.sunny.Sunny.entity.Category;
import com.sunny.Sunny.model.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
}
