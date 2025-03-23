package com.mayuktha.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @NoArgsConstructor @AllArgsConstructor
public class ProductsDto{
	String name;
	String description;
	String sku;
	float price;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	int categoryId;
	int quantity ;
	String category;
}
