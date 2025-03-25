package com.mayuktha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuktha.products.dto.CategoryDto;
import com.mayuktha.products.dto.SubCategoriesDto;
import com.mayuktha.products.response.Response;
import com.mayuktha.products.service.ICategoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "/v1/category",produces = {MediaType.APPLICATION_JSON_VALUE})
public class CategoryController {
	
	@Autowired
	ICategoryService iCategoriesService;

	@GetMapping("/fetchAll")
	public void fetchAllCategories() {
		
	}
	
	@GetMapping("/{id}")
	public String getMethodName(@PathVariable int id) {
		return new String();
	}
	
	
	@PostMapping
	public ResponseEntity createCategory(@RequestBody CategoryDto categoriesDto) {
		
		iCategoriesService.savecategory(categoriesDto);
		Response<String> apiResponse = new Response<String>(HttpStatus.CREATED.value(),"SUCCESS","Record has been Created Successfully",null);
		// int statusCode,String status,String message,T data
		 
		return ResponseEntity.ok().body(apiResponse);
	}
	
	
	/*
	
	@PostMapping("/subcategories")
	public ResponseEntity createSubCategory(@RequestBody SubCategoriesDto subCategoriesDto) {
		
		iCategoriesService.saveSubCategories(subCategoriesDto);
		ApiResponse<String> apiResponse = new ApiResponse<String>(HttpStatus.CREATED.value(),"SUCCESS","Record has been Created Successfully",null);
		return ResponseEntity.ok().body(apiResponse);
	}
	
	*/
	
}
