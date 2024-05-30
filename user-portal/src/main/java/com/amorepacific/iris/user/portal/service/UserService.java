package com.amorepacific.iris.user.portal.service;

import java.util.Optional;

import com.amorepacific.iris.user.portal.dto.UserDto;

public interface UserService {

	Optional<UserDto> login(UserDto userDto);
}
