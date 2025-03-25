package com.mayuktha.mapper;

import java.time.LocalDateTime;

import com.mayuktha.products.dto.CategoryDto;
import com.mayuktha.products.dto.ProductsDto;
import com.mayuktha.products.dto.SubCategoriesDto;
import com.mayuktha.products.entity.Category;
import com.mayuktha.products.entity.Products;

public class MapperUtil {

	public static Category mapToCategories(CategoryDto categoryDto,Category category) {
		category.setName(categoryDto.categoryName());
		category.setDescription(categoryDto.description());
		category.setCreatedAt(LocalDateTime.now());
		category.setParentCategoryID(categoryDto.parentCategoryID());
		return category;
	}
	/*
	public static SubCategories mapToSubCategories(SubCategoriesDto subCategoriesDto,SubCategories subCategories) {
		subCategories.setCategoryId(subCategoriesDto.category_id());
		subCategories.setDescription(subCategoriesDto.description());
		subCategories.setSubcategoryname(subCategoriesDto.sub_category_name());
		subCategories.setCreatedAt(LocalDateTime.now());
		subCategories.setParentCategoryId(subCategoriesDto.parent_category_id());
		return subCategories;
	}
	*/
	public static Products mapToProduct(ProductsDto productsDto,Products products) {
		
		products.setName(productsDto.getName());
		products.setDescription(productsDto.getDescription());
		products.setUnitPrice(productsDto.getPrice());
		products.setSku(productsDto.getSku());
		products.setCreatedAt(LocalDateTime.now());
		products.setCategoryID(productsDto.getCategoryId());
		return products;
	}
	
	public static ProductsDto mapToProductsDto(Products products,ProductsDto productsDto) {
		productsDto.setName(products.getName());
		productsDto.setPrice(products.getUnitPrice());
		productsDto.setDescription(products.getDescription());
		productsDto.setCategoryId(products.getCategoryID());
		productsDto.setSku(products.getSku());
		return productsDto;
	}
}
