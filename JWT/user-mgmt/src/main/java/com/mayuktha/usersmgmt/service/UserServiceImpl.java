package com.mayuktha.usersmgmt.service;


import java.net.ResponseCache;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.auth.InvalidCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mayuktha.usersmgmt.dto.AddressDto;
import com.mayuktha.usersmgmt.dto.JwtTokenResponse;
import com.mayuktha.usersmgmt.dto.LoginRequest;
import com.mayuktha.usersmgmt.dto.UserAggregatorResponse;
import com.mayuktha.usersmgmt.dto.UserDto;
import com.mayuktha.usersmgmt.entity.Address;
import com.mayuktha.usersmgmt.entity.iUser;
import com.mayuktha.usersmgmt.exception.ResourceNotFoundException;
import com.mayuktha.usersmgmt.exception.UserAlreadyExistedException;
import com.mayuktha.usersmgmt.exception.UserNotFoundException;
import com.mayuktha.usersmgmt.jwt.JwtUtil;
import com.mayuktha.usersmgmt.mapper.AddressMapper;
import com.mayuktha.usersmgmt.mapper.UserMapper;
import com.mayuktha.usersmgmt.repository.AddressRepository;
import com.mayuktha.usersmgmt.repository.UserRepository;

@Service
public class UserServiceImpl implements IUsersService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public iUser createUser(UserDto userDto) {
		Optional<iUser> savedUserList=userRepository.findByEmail(userDto.getEmail());
		log.debug("UserServiceImpl::::::savedUserList::::::{}",savedUserList);
		iUser iUser=null;
		// iUser iUser=savedUserList.orElseThrow(()-> new UserAlreadyExistedException("User is Already Existed with :"+userDto.getEmail()));
		if(savedUserList.isPresent()) {
			iUser=savedUserList.get();
			throw new UserAlreadyExistedException("User is Already Existed with :"+userDto.getEmail());
		}
		if(null!=iUser ) {
			List<Address> addressList = addressRepository.findByMobile(userDto.getAddress().getMobile());
			if(null!=addressList && addressList.size()>0) {
				throw new UserAlreadyExistedException("User is Already Existed with :"+userDto.getAddress().getMobile());
			}
		}
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(); 
		iUser user=UserMapper.mapToUser(userDto, new iUser());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Address address = AddressMapper.mapToAddress(userDto.getAddress(), new Address());
		addressRepository.save(address);
		user.setAddress(address);
		return userRepository.save(user);
		
	}
	
	public List<UserDto> fetchAllUsers() {
		List<iUser> userList=userRepository.findAll();
		log.debug("userList:::{}",userList.size());
		List<UserDto> userDtoList = null;
		if(userList!=null && userList.size()>0) {
			userDtoList=userList.stream().map(user->UserMapper.mapToUserDto(user, new UserDto())).collect(Collectors.toList());
		}else {
			log.debug("userList:::EMPTY");
			throw new UserNotFoundException("Users are not Available");
		}
		return userDtoList;
	}

	@Override
	public void deleteUser(Long id) {
		iUser user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User with ID: "+ id +" is Not existed"));
		userRepository.delete(user);
	}
	@Override
	public boolean updateUser(UserDto userDto) {
		boolean isUpdated = false;
		iUser user = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "UserId", String.valueOf(userDto.getId()))
        );
		
	UserMapper.mapToUser(userDto, user);
	userRepository.save(user);
	isUpdated = true;
	 return isUpdated;
	}

	@Override
	public JwtTokenResponse loginUser(LoginRequest loginRequest) throws InvalidCredentialsException {
		 iUser user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new UserNotFoundException("Users are not Available"));
        
		 if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
	            throw new InvalidCredentialsException("Password does not match");
	        }
	        String token = jwtUtil.generateToken(user.getEmail(),user.getRole());

	        return JwtTokenResponse.builder()
	                .status(200)
	                .message("User Successfully Logged In")
	                .token(token)
	                .expirationTime("6 Month")
	                .role(user.getRole())
	                .build();
	}
	@Override
	public Long getUserIdByEmail(String email) {
		iUser user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Users are not Available"));
		return user.getId();
	}
	
}
