package com.mayuktha.orders.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mayuktha.orders.dto.Response;

@FeignClient(name = "PRODUCTS",path = "/product")
public interface ProductClient {
	
	@GetMapping("/get-by-product-id/{productId}")
    public ResponseEntity<Response> getProductById(@PathVariable Long productId);
}
