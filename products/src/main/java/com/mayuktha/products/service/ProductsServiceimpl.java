package com.mayuktha.products.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayuktha.mapper.MapperUtil;
import com.mayuktha.products.dto.ProductsDto;
import com.mayuktha.products.entity.Category;

import com.mayuktha.products.entity.Products;
import com.mayuktha.products.exception.ResourceNotFoundException;
import com.mayuktha.products.repository.CategoryJparepository;
import com.mayuktha.products.repository.ProductsJpaRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductsServiceimpl implements IProductsService{

	private static final Logger log = LoggerFactory.getLogger(ProductsServiceimpl.class);
	
	@Autowired
	ProductsJpaRepository productsJpaRepository;
	
	@Autowired
	CategoryJparepository categoryJparepository;
	
	
	
	@Override
	@Transactional
	public Products saveProducts(ProductsDto productsDto) {
		Products products = MapperUtil.mapToProduct(productsDto, new Products());
		products=productsJpaRepository.save(products);
		log.debug("ProductsServiceimpl:::product ID :::{}",products.getProductID());
		
		return products;
	}


	@Override
	public ProductsDto fetchProducts(int productId) {
		log.debug("ProductsServiceimpl:::fetchProducts::START::productId :::{}",productId);
		Products product=productsJpaRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("products", "ProductId", String.valueOf(productId)));
		ProductsDto productsDto = MapperUtil.mapToProductsDto(product, new ProductsDto());
		log.debug("ProductsServiceimpl:::productsDto:::: :::{}",productsDto);
		Optional <Category> cateOptional = categoryJparepository.findById(productsDto.getCategoryId());
		log.debug("ProductsServiceimpl:::cateOptional:::: :::{}",cateOptional);
		if(cateOptional.isPresent()) {
			productsDto.setCategory(cateOptional.get().getName());
		}
		return productsDto;
	}


	@Override
	public List<Products> fetchAllProducts() {
		
		// List<Products> productsList = productsJpaRepository.findAll();
		
		List <Object[]> productCategory = productsJpaRepository.getPoductAndCategoryNative(1);
		log.debug("ProductsServiceimpl:::fetchAllProducts::::::::productCategory :::{}",productCategory.size());
		for (Object[] result : productCategory) {
            String name = (String) result[0];
            String cname = (String) result[1];
            System.out.println("name: " + name + ", cname: " + cname);
        }
		return null;
	}
	
	private void parseProducts() {
		
	}
	
}
