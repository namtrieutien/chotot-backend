package com.sunny.Sunny.controller;

import com.sunny.Sunny.model.dto.CategoryDto;
import com.sunny.Sunny.model.dto.PostDto;
import com.sunny.Sunny.model.dto.UserDTO;
import com.sunny.Sunny.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<?> getAllUser() {
        List<UserDTO> userDTOList = adminService.getAllUser();
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping(path = "/posts")
    @ResponseBody
    public ResponseEntity<?> getAllPost() {
        List<PostDto> postDtoList = adminService.getAllPost();
        return ResponseEntity.ok(postDtoList);
    }

    @GetMapping(path = "/categories")
    @ResponseBody
    public ResponseEntity<?> getAllCategories() {
        List<CategoryDto> categoryDtoList = adminService.getAllCategory();
        return ResponseEntity.ok(categoryDtoList);
    }

}
