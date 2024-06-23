package com.amorepacific.iris.user.portal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.amorepacific.iris.user.portal.dto.UserDto;
import com.amorepacific.iris.user.portal.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/test")
    public void home(HttpServletRequest req, HttpServletResponse res, UserDto userDto) throws IOException {
		
		System.out.println(userDto.toString());
		UserDto dto = userService.login(userDto).get();
		
		System.out.println(dto.toString());
    }
}
