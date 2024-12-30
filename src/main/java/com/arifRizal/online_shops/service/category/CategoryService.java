package com.arifRizal.online_shops.service.category;

import com.arifRizal.online_shops.exception.CategoryNotFoundException;
import com.arifRizal.online_shops.model.Category;
import com.arifRizal.online_shops.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category getCategoryById(Long id) {
       return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {
       categoryRepository.findById(id)
               .ifPresentOrElse(categoryRepository::delete, () -> {
           throw new CategoryNotFoundException("Category Not Found");
       });
    }
}
