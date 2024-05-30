package com.amorepacific.iris.user.portal.service.impl;

import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amorepacific.iris.user.portal.dto.UserDto;
import com.amorepacific.iris.user.portal.mapper.UserMapper;
import com.amorepacific.iris.user.portal.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
    private SqlSessionTemplate db1SqlSessionTemplate;
	
	@Override
	public Optional<UserDto> login(UserDto userDto) {
		
		UserMapper um = db1SqlSessionTemplate.getMapper(UserMapper.class);
        return um.login(userDto);
	}

}
