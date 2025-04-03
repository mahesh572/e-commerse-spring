package com.mayuktha.products.mapper;

import org.springframework.stereotype.Component;

import com.mayuktha.products.dto.CategoryDto;
import com.mayuktha.products.dto.ProductDto;
import com.mayuktha.products.entity.Category;
import com.mayuktha.products.entity.Product;

@Component
public class EntityDtoMapper {

    //Category to DTO basic
    public CategoryDto mapCategoryToDtoBasic(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }


    
    //Product to DTO Basic
    public ProductDto mapProductToDtoBasic(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }

}
