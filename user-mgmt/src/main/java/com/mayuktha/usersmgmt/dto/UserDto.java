package com.mayuktha.usersmgmt.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class UserDto {
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int id;
	
	private String userName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message = "Password Must not be Empty or Null")
	private String password;
	private String firstName;
	private String lastName;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	@Email(message = "Provide Valid EmailAddress")
	private String email;
	@Valid
	private AddressDto address;
	 
}
