package com.sunny.Sunny.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class CategoryDto {
    private Integer id;
    private String name;
    private String description;
}
