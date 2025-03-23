package com.mayuktha.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mayuktha.products.entity.Products;
@Repository
public interface ProductsJpaRepository extends JpaRepository<Products, Integer>{
	
	
	@Query(value = "SELECT p.name, c.name FROM Product p JOIN Category c ON p.categoryID = c.categoryID WHERE p.productID = :productID", nativeQuery = true)
    List<Object[]> getPoductAndCategoryNative(@Param("productID") int productID);

}
