package com.ecommerse.BuyNest.service;

import com.ecommerse.BuyNest.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();
    void createCategory(Category category);
    void deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
