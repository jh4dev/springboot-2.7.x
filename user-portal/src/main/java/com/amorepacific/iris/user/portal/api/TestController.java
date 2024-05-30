package com.amorepacific.iris.user.portal.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amorepacific.iris.user.portal.common.util.ConvertUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

	@GetMapping("/test/logtest")
    public void home(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Package currentPackage = TestController.class.getPackage();
        String packageName = currentPackage != null ? currentPackage.getName() : "default package";

		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>> Current pacakge : {}", packageName);
		
		ConvertUtil.logTest();
		
    	System.out.println("----------- Welcome");
    	res.sendRedirect("/aaa");
    }
}
