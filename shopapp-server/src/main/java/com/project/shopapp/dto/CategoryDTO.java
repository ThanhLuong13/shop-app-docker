package com.project.shopapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 50, message = "Category's name must be between 3 and 50")
    private String name;
}
