package com.mayuktha.usersmgmt.controller;

import org.apache.http.auth.InvalidCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuktha.usersmgmt.dto.LoginRequest;
import com.mayuktha.usersmgmt.dto.UserDto;
import com.mayuktha.usersmgmt.entity.iUser;
import com.mayuktha.usersmgmt.response.ApiResponse;
import com.mayuktha.usersmgmt.service.IUsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@Autowired
	IUsersService iUsersService;
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity loginuser(@RequestBody LoginRequest loginRequest) throws InvalidCredentialsException {
		
		return ResponseEntity.ok(iUsersService.loginUser(loginRequest));
	}
	
	@PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto registrationRequest){
        System.out.println(registrationRequest);
        log.debug("AuthController::::registrationRequest::{}",registrationRequest);
        
        iUser user = iUsersService.createUser(registrationRequest);
        ApiResponse.ApiResponseBuilder<Object> apiResponse= ApiResponse.builder();
		
		if(user!=null) {
			apiResponse.statusCode(HttpStatus.OK.value());
			apiResponse.status("SUCCESS");
			apiResponse.message("Record Has been Successfully Created");
		}else {
		    apiResponse.statusCode(1);
			apiResponse.status("FAIL");
			apiResponse.message("ERROR");
		}
		log.debug("AuthController:::::registerUser:::::{}",apiResponse.build());
		return ResponseEntity.ok().body(apiResponse.build());
       
    }
	
	// @PostMapping("/register")
}
