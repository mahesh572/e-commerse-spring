package com.mayuktha.orders.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class AddressDto {
	
	private String address_line1;
	private String address_line2;
	private String city;
	private String postal_code;
	
	private String country;
	
	
	private String mobile;

}
