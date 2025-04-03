package com.mayuktha.gateway.usersmgmt.security.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mayuktha.gateway.usersmgmt.entity.iUser;
import com.mayuktha.gateway.usersmgmt.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Mono<UserDetails> findByUsername(String email) {
		
		logger.debug("UserDetailsServiceImpl:::::findByUsername::::{}",email);
		
		iUser user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
		List<GrantedAuthority> grantedList = List.of(simpleGrantedAuthority);
		User userdetails = new User(user.getEmail(), user.getPassword(), grantedList);
		logger.debug("UserDetailsServiceImpl:::::findByUsername::userdetails::::{}",userdetails);
		return Mono.just(userdetails);
		
		
	}

}
