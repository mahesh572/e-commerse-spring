package com.mayuktha.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayuktha.products.entity.Category;

@Repository
public interface CategoryJparepository extends JpaRepository<Category, Integer>{

}
