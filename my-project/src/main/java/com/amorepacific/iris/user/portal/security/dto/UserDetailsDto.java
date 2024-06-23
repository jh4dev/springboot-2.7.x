package com.amorepacific.iris.user.portal.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.amorepacific.iris.user.portal.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public class UserDetailsDto implements UserDetails {

	@Delegate
	private UserDto userDto;
	
	private Collection<? extends GrantedAuthority> autorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorities;
	}

	@Override
	public String getPassword() {
		return userDto.getUserPw();
	}

	@Override
	public String getUsername() {
		return userDto.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	
}
