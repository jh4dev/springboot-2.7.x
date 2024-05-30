package com.amorepacific.iris.user.portal.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.amorepacific.iris.user.portal.common.constants.AuthConstants;
import com.amorepacific.iris.user.portal.common.util.ConvertUtil;
import com.amorepacific.iris.user.portal.common.util.JwtUtil;
import com.amorepacific.iris.user.portal.dto.UserDto;
import com.amorepacific.iris.user.portal.security.dto.UserDetailsDto;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

/**
 * 사용자의 ‘인증’에 대해 성공하였을 경우 수행되는 Handler로 성공에 대한 사용자에게 반환값을 구성하여 전달합니다
 */
@Slf4j
@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.info("3.CustomLoginSuccessHandler");

        // [STEP1] 사용자와 관련된 정보를 모두 조회합니다.
        UserDto userDto = ((UserDetailsDto) authentication.getPrincipal()).getUserDto();

        // [STEP2] 조회한 데이터를 JSONObject 형태로 파싱을 수행합니다.
        JsonNode userVoObj = null;
        try {
        	userVoObj = ConvertUtil.objectToJsonNode(userDto);
        } catch (Exception e) {
        	e.printStackTrace();
		}

        HashMap<String, Object> responseMap = new HashMap<>();

        JsonNode resNode = null;

        // [STEP3-1] 사용자의 상태가 '휴면 상태' 인 경우 응답 값으로 전달 할 데이터
        if ("D".equals(userDto.getUserRoleCd())) {
            responseMap.put("userInfo", userVoObj);
            responseMap.put("resultCode", 9001);
            responseMap.put("token", null);
            responseMap.put("failMsg", "휴면 계정입니다.");
            try {
				resNode = ConvertUtil.mapToPojo(responseMap, JsonNode.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
        }

        // [STEP3-2] 사용자의 상태가 '휴면 상태'가 아닌 경우 응답 값으로 전달 할 데이터
        else {
            // 1. 일반 계정일 경우 데이터 세팅
            responseMap.put("userInfo", userVoObj);
            responseMap.put("resultCode", 200);
            responseMap.put("failMsg", null);
            try {
				resNode = ConvertUtil.mapToPojo(responseMap, JsonNode.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

             String token = JwtUtil.generateJwtToken(userDto);
             response.addHeader(AuthConstants._AUTH_HEADER, AuthConstants._TOKEN_TYPE + " " + token);
        }

        // [STEP4] 구성한 응답 값을 전달합니다.
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resNode);  // 최종 저장된 '사용자 정보', '사이트 정보' Front 전달
        printWriter.flush();
        printWriter.close();
    }
}