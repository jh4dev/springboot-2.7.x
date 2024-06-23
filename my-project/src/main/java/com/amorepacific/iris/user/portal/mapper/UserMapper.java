package com.amorepacific.iris.user.portal.mapper;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.amorepacific.iris.user.portal.dto.UserDto;

@Repository
public interface UserMapper {

	Optional<UserDto> login(UserDto userDto);
}
