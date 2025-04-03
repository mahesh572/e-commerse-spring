package com.mayuktha.usersmgmt.service;

import java.util.List;

import org.apache.http.auth.InvalidCredentialsException;

import com.mayuktha.usersmgmt.dto.JwtTokenResponse;
import com.mayuktha.usersmgmt.dto.LoginRequest;
import com.mayuktha.usersmgmt.dto.UserAggregatorResponse;
import com.mayuktha.usersmgmt.dto.UserDto;
import com.mayuktha.usersmgmt.entity.iUser;


public interface IUsersService {
	iUser createUser(UserDto userDto);
	List<UserDto> fetchAllUsers();
	void deleteUser(Long id);
	boolean updateUser(UserDto userDto);
	JwtTokenResponse loginUser(LoginRequest loginRequest) throws InvalidCredentialsException;
	public Long getUserIdByEmail(String email); 
}
