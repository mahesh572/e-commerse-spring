package com.mayuktha.usersmgmt.mapper;
import com.mayuktha.usersmgmt.entity.Address;

import com.mayuktha.usersmgmt.dto.AddressDto;

public class AddressMapper {

	public static Address mapToAddress(AddressDto addressDto,Address address) {
		System.out.println("address:::::::::"+address);
		address.setAddressLine1(addressDto.getAddress_line1());
		address.setAddressLine2(addressDto.getAddress_line2());
		address.setCity(addressDto.getCity());
		address.setCountry(addressDto.getCountry());
		address.setMobile(addressDto.getMobile());
		address.setPostalCode(addressDto.getPostal_code());
		return address;
	}
}
