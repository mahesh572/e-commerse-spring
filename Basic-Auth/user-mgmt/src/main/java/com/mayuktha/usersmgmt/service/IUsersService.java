package com.mayuktha.usersmgmt.service;

import java.util.List;

import com.mayuktha.usersmgmt.dto.UserDto;
import com.mayuktha.usersmgmt.entity.iUser;


public interface IUsersService {
	iUser createUser(UserDto userDto);
	List<UserDto> fetchAllUsers();
	void deleteUser(int id);
	boolean updateUser(UserDto userDto);
}
