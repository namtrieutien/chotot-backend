package com.sunny.Sunny.model.mapper;


import com.sunny.Sunny.entity.Post;
import com.sunny.Sunny.model.dto.PostDto;

public class PostMapper {
    public static PostDto toPostDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setPrice(post.getPrice());
        postDto.setSize(post.getSize());
        postDto.setUser_id(post.getUser().getId());
        return postDto;
    }
}
