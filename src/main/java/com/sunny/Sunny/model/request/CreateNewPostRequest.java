package com.sunny.Sunny.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateNewPostRequest {
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "price is required")
    private Long price;

    @NotBlank(message = "size is required")
    private String size;

    @NotNull(message = "categorize_id is required")
    private Integer categorize_id;
}
