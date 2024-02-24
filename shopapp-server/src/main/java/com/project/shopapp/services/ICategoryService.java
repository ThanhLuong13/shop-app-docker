package com.project.shopapp.services;

import com.project.shopapp.dto.CategoryDTO;
import com.project.shopapp.model.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    CategoryEntity createCategory(CategoryDTO category);
    CategoryEntity getCategoryById(int id);
    List<CategoryEntity> getAllCategories();
    CategoryEntity updateCategory(int categoryId, CategoryDTO category);
    void deleteCategory(int id);
}
