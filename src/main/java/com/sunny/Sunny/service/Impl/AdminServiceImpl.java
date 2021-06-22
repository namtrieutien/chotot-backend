package com.sunny.Sunny.service.Impl;

import com.sunny.Sunny.model.dto.CategoryDto;
import com.sunny.Sunny.model.dto.PostDto;
import com.sunny.Sunny.model.dto.UserDTO;
import com.sunny.Sunny.model.mapper.CategoryMapper;
import com.sunny.Sunny.model.mapper.PostMapper;
import com.sunny.Sunny.model.mapper.UserMapper;
import com.sunny.Sunny.repository.CategoryRepository;
import com.sunny.Sunny.repository.PostRepository;
import com.sunny.Sunny.repository.UserRepository;
import com.sunny.Sunny.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(PostMapper::toPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }



}
