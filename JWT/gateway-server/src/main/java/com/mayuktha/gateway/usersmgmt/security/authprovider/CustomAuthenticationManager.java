package com.mayuktha.gateway.usersmgmt.security.authprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mayuktha.gateway.usersmgmt.security.service.UserDetailsServiceImpl;

import reactor.core.publisher.Mono;

@Component
public class CustomAuthenticationManager implements ReactiveAuthenticationManager{

	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl; 
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationManager.class);
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		
		logger.debug("CustomAuthenticationManager::::::authenticate:::::START");
        
        String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		//logger.debug("CustomUsernamePasswordAuthenticationProvider::::authenticate:::::{}",username);
		Mono<UserDetails> monouserDetails = userDetailsServiceImpl.findByUsername(username);
		UserDetails userDetails = monouserDetails.block();
		logger.debug("CustomAuthenticationManager::::::authenticate:::::userDetails:::{}",userDetails);
	//	if(passwordEncoder.encode(pwd).equalsIgnoreCase(userDetails.getPassword())) {
		if(passwordEncoder.matches(pwd, userDetails.getPassword())) {
			return Mono.just(new UsernamePasswordAuthenticationToken(username,pwd, userDetails.getAuthorities()));
		}else {
			throw new BadCredentialsException("Invalid password!");
		}
		
	}

}
