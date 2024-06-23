package com.amorepacific.iris.user.portal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public void home(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	System.out.println("----------- Welcome");
    	res.sendRedirect("/aaa");
    }
    
    @GetMapping("/aaa")
    public String aaa() {
    	return "This is aaa";
    }
}