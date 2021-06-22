package com.sunny.Sunny.service;

import com.sunny.Sunny.model.dto.CategoryDto;
import com.sunny.Sunny.model.dto.PostDto;
import com.sunny.Sunny.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<UserDTO> getAllUser();
    List<PostDto> getAllPost();
    List<CategoryDto> getAllCategory();
}
