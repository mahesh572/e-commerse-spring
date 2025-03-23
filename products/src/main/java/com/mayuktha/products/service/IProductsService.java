package com.mayuktha.products.service;

import java.util.List;

import com.mayuktha.products.dto.ProductsDto;
import com.mayuktha.products.entity.Products;

public interface IProductsService {
	
	Products saveProducts(ProductsDto productsDto);
	ProductsDto fetchProducts(int productId);
	List<Products> fetchAllProducts();

}
