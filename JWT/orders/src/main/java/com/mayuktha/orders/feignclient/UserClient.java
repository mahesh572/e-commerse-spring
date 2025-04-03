package com.mayuktha.orders.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mayuktha.orders.response.ApiResponse;

@FeignClient(name = "USER-MGMT",path = "/users")
public interface UserClient {
	
	@GetMapping("/userid/{email}")
	public ResponseEntity<ApiResponse<Long>> fetchUserId(@PathVariable String email);

}
