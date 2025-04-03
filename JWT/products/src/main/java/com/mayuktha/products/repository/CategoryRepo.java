package com.mayuktha.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mayuktha.products.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
