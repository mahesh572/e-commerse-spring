package com.mayuktha.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayuktha.mapper.MapperUtil;
import com.mayuktha.products.dto.CategoryDto;
import com.mayuktha.products.dto.SubCategoriesDto;
import com.mayuktha.products.entity.Category;
import com.mayuktha.products.repository.CategoryJparepository;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	CategoryJparepository categoryJparepository;
	
	
	@Override
	public void savecategory(CategoryDto categoryDto) {
		
		Category category = MapperUtil.mapToCategories(categoryDto, new Category());
		category = categoryJparepository.save(category);
	}
	
	/*
	@Override
	public void saveSubCategories(SubCategoriesDto subCategoriesDto) {
		SubCategories subCategories = MapperUtil.mapToSubCategories(subCategoriesDto, new SubCategories());
		subCategoriesJpaRepository.save(subCategories);
	}
	
	*/
}
