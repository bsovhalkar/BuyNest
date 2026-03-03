package com.ecommerse.BuyNest.repositories;

import com.ecommerse.BuyNest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}