package com.mayuktha.usersmgmt.mapper;

import java.time.LocalDateTime;

import com.mayuktha.usersmgmt.dto.AddressDto;
import com.mayuktha.usersmgmt.dto.UserDto;
import com.mayuktha.usersmgmt.entity.Address;
import com.mayuktha.usersmgmt.entity.User;



public class UserMapper {

	public static User mapToUser(UserDto userDto,User user) {
		// user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setCreatedAt(LocalDateTime.now());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		// user.setTelephone(userDto.getTelephone());
		
		AddressDto addressDto = userDto.getAddress();
		
		Address address = new Address();
		address.setAddressLine1(addressDto.getAddress_line1());
		address.setAddressLine2(addressDto.getAddress_line2());
		address.setCity(addressDto.getCity());
		address.setCountry(addressDto.getCountry());
		address.setPostalCode(addressDto.getPostal_code());
		address.setMobile(addressDto.getMobile());
		
		user.setAddress(address);
		return user;
	}
	
	public static UserDto mapToUserDto(User user,UserDto userDto) {
		userDto.setEmail(user.getEmail());
		// userDto.setUserName(user.getUserName());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setCreatedAt(user.getCreatedAt());
		Address address=user.getAddress();
		AddressDto addressDto = new AddressDto();
		addressDto.setAddress_line1(address.getAddressLine1());
		addressDto.setAddress_line2(address.getAddressLine2());
		addressDto.setCity(address.getCity());
		addressDto.setCountry(address.getCountry());
		addressDto.setPostal_code(address.getPostalCode());
		addressDto.setMobile(address.getMobile());
		userDto.setAddress(addressDto);
		return userDto;
	}
}
