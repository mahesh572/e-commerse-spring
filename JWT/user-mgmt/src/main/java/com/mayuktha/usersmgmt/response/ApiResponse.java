package com.mayuktha.usersmgmt.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor @Builder
public class ApiResponse<T> {

	private int statusCode;
	private String status;
	private String message;
	private T data;
	
	
}
