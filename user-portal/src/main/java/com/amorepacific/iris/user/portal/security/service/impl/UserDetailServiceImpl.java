package com.amorepacific.iris.user.portal.security.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amorepacific.iris.user.portal.dto.UserDto;
import com.amorepacific.iris.user.portal.security.dto.UserDetailsDto;
import com.amorepacific.iris.user.portal.service.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDto user = UserDto.builder()
						.userId(username)
						.build();

		if(username == null || "".equals(username)) throw new UsernameNotFoundException(username);
		
		return userService.login(user)
                .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
                .orElseThrow(() -> new AuthenticationServiceException(username));
	}

}
