package com.mayuktha.gateway.usersmgmt.security.authprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mayuktha.gateway.usersmgmt.security.service.UserDetailsServiceImpl;

@Service
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUsernamePasswordAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.debug("CustomUsernamePasswordAuthenticationProvider::::authenticate:::START::");
		/*
		
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		logger.debug("CustomUsernamePasswordAuthenticationProvider::::authenticate:::::{}",username);
		UserDetails userDetails = userDetailsServiceImpl.findByUsername(username);
		
	//	if(passwordEncoder.encode(pwd).equalsIgnoreCase(userDetails.getPassword())) {
		if(passwordEncoder.matches(pwd, userDetails.getPassword())) {
			return new UsernamePasswordAuthenticationToken(username,pwd, userDetails.getAuthorities());
		}else {
			throw new BadCredentialsException("Invalid password!");
		}
		*/
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
