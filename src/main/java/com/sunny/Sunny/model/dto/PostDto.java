package com.sunny.Sunny.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class PostDto {
    private Integer id;
    private String title;
    private String description;
    private Long price;
    private String size;
    private Integer user_id;
}
