package com.mayuktha.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuktha.config.ProductConfigProperties;
import com.mayuktha.products.dto.ErrorResponseDto;
import com.mayuktha.products.dto.ProductsDto;
import com.mayuktha.products.response.ErrorDetails;
import com.mayuktha.products.response.Response;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.mayuktha.products.service.IProductsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping(path = "/v1/products",produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Product Controller", description = "APIs for managing products")
public class ProductsController {

	private static final Logger log = LoggerFactory.getLogger(ProductsController.class);
	
	
	@Autowired
	IProductsService iProductsService;
	
	@Autowired
	ProductConfigProperties productConfigProperties;
	
	@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorDetails.class)
                )
        )
}
)
	@PostMapping
	public ResponseEntity saveProducts(@RequestBody ProductsDto productsDto) {
		iProductsService.saveProducts(productsDto);
		Response<String> apiResponse = new Response<String>(HttpStatus.CREATED.value(),"SUCCESS","Record has been Created Successfully",null);
		return ResponseEntity.ok().body(apiResponse);
	}
	
	
	@GetMapping("/{productId}")
	public ResponseEntity<Response<ProductsDto>> fetchProducts(@PathVariable int productId ) {
		log.debug("ProductsController::fetchProducts::::"+productId);
		ProductsDto productsDto = iProductsService.fetchProducts(productId);
		log.debug("ProductsController::productsDto::::"+productsDto);
		Response<ProductsDto> apiResponse = new Response<ProductsDto>(HttpStatus.OK.value(),"SUCCESS","",productsDto);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping
	public void fetchAllProducts() {
		iProductsService.fetchAllProducts();
		
	}
		
	@ApiResponses({
	        @ApiResponse(
	                responseCode = "200",
	                description = "HTTP Status OK"
	        ),
	        @ApiResponse(
	                responseCode = "500",
	                description = "HTTP Status Internal Server Error",
	                content = @Content(
	                        schema = @Schema(implementation = ErrorDetails.class)
	                )
	        )
	}
	)
	@GetMapping(path = "contact")
	public ResponseEntity<Object> contactInfo() {
		// userContactInfoProperties
		return ResponseEntity.ok().body(productConfigProperties);
		
	}
}
