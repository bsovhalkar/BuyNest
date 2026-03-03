package com.ecommerse.BuyNest.service;

import com.ecommerse.BuyNest.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    private List<Category> categoryList = new ArrayList<>();
    private Long categoryId = 1L;
    @Override
    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(categoryId++);
        categoryList.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryList.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElse(null);
        if (category != null) {
            categoryList.remove(category);
        }
        else {
            return "Category not found";
        }
        return "success";

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category curr = categoryList.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElse(null);
        if (curr != null) {
            curr.setCategoryId(category.getCategoryId());
            curr.setCategoryName(category.getCategoryName());
            return curr;

        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }

}
