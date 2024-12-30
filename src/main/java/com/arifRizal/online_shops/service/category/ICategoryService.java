package com.arifRizal.online_shops.service.category;

import com.arifRizal.online_shops.model.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(Category category);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);
}
