package com.mayuktha.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuktha.products.dto.SupplierDto;

@RestController
@RequestMapping(path = "/v1/supplier")
public class SupplierController {

	
	@PostMapping
	public void createSupplier(@RequestBody SupplierDto supplierDto) {
		
	}
	
}
