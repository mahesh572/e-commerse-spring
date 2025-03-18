package com.mayuktha.usersmgmt.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuktha.usersmgmt.constants.Constants;
import com.mayuktha.usersmgmt.dto.UserDto;
import com.mayuktha.usersmgmt.entity.User;
import com.mayuktha.usersmgmt.response.ApiResponse;
import com.mayuktha.usersmgmt.service.IUsersService;

@RestController
@RequestMapping(path = "/user-mgmt/v1/users",produces = {MediaType.APPLICATION_JSON_VALUE})
public class UsersController {
	
	private static final Logger log = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	IUsersService iUsersService;
	
	@Autowired
	Environment environment;  // Environment variables are OS level properties like PATH,CLASSPATH,JAVA_HOME
	
	@PostMapping
	public ResponseEntity createUser(@RequestBody UserDto userDto) {
		
		log.debug("UserRegistrationController::::createUser::::"+userDto);
		User user = iUsersService.createUser(userDto);
		ApiResponse<String> apiResponse=new ApiResponse<>();
		if(user!=null) {
			apiResponse.setStatusCode(HttpStatus.CREATED.value());
			apiResponse.setStatus("SUCCESS");
			apiResponse.setMessage("Record Has been Successfully Created");
		}else {
			// apiResponse.setStatusCode(HttpStatus.CREATED.value());
			apiResponse.setStatus("FAIL");
			apiResponse.setMessage("ERROR");
		}
		return ResponseEntity.ok().body(apiResponse);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		iUsersService.deleteUser(id);
		ApiResponse<String> apiResponse=new ApiResponse<>();
		apiResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
		apiResponse.setStatus("SUCCESS");
		apiResponse.setMessage("Record Has been Successfully Deleted.");
		return ResponseEntity.noContent().build().ofNullable(apiResponse);
	}
	@PutMapping
	public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto) {
		
		log.debug("UserRegistrationController::::updateUser::::"+userDto);
		boolean isUpdated = iUsersService.updateUser(userDto);
		ApiResponse<String> apiResponse=new ApiResponse<>();
		if(isUpdated) {
			apiResponse.setStatusCode(HttpStatus.OK.value());
			apiResponse.setStatus("SUCCESS");
			apiResponse.setMessage(Constants.STATUS_200);
		}else {
			apiResponse.setStatusCode(HttpStatus.OK.value());
			apiResponse.setStatus(HttpStatus.EXPECTATION_FAILED.toString());
			apiResponse.setMessage(Constants.MESSAGE_417_UPDATE);
		}
		
		return ResponseEntity.ok().body(apiResponse);
	}
	@GetMapping(path  = "env")
	public ResponseEntity<Object> envVariable() {
		return ResponseEntity.ok().body(environment.getProperty("JAVA_HOME"));
	}
	@GetMapping
	public ResponseEntity hello() {
		
		return ResponseEntity.ok("Hello.........");
	}
}
