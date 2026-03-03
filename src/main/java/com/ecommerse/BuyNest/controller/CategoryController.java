package com.ecommerse.BuyNest.controller;

import com.ecommerse.BuyNest.model.Category;
import com.ecommerse.BuyNest.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")   // Base URL (optional but recommended)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET
    @RequestMapping(value = "/public/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> tmp = categoryService.getCategoryList();
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    // POST
    @RequestMapping(value = "/public/categories", method = RequestMethod.POST)
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    // DELETE
    @RequestMapping(value = "/admin/categories/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {

        String status = categoryService.deleteCategory(categoryId);

        if ("success".equals(status)) {
            return ResponseEntity.ok("Category deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category not found");
        }
    }

    // PUT
    @RequestMapping(value = "/public/categories/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(
            @RequestBody Category category,
            @PathVariable Long categoryId) {

        try {
            categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}