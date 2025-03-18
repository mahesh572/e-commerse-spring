package com.mayuktha.usersmgmt.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
	@NotEmpty(message = "Country must not be Empty")
	private String country;
	private String telephone;
	@Size(min = 10,max = 10)
	private String mobile;

}
