package com.project.shopapp.services.servicesImp;

import com.project.shopapp.dto.CategoryDTO;
import com.project.shopapp.model.CategoryEntity;
import com.project.shopapp.repositories.CategoryRepository;
import com.project.shopapp.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {


    private final CategoryRepository categoryRepository;


    @Override
    public CategoryEntity createCategory(CategoryDTO categoryDTO) {
        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setName(categoryDTO.getName());
        return categoryRepository.save(newCategory);
    }

    @Override
    public CategoryEntity getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public CategoryEntity updateCategory(int categoryId,
                                   CategoryDTO categoryDTO) {
        CategoryEntity existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    @Transactional
    public void deleteCategory(int id) {
        //xóa xong
        categoryRepository.deleteById(id);
    }

}
