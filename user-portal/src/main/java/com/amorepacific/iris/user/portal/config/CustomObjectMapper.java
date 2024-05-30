package com.amorepacific.iris.user.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class CustomObjectMapper {

	/**
	 * @method create
	 * @description 커스텀 ObjectMapper create
	 * 	<br/>	1) DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
	 *  <br/> 	- 역직렬화시, 선언되지 않은 속성이 있는경우 에러 발생하지 않도록 설정
	 *  <br/>	
	 *  <br/>	2) BigDecimalSerializer
	 *  <br/>	- 직렬화 시, BigDecimal to String 되도록 설정
	 *  
	 * */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		/* ObjectMapper에 필요한 설정 아래 적용 */
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        /* 직렬화 모듈 추가 Numeric to String */
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new StringDeserializer());
        
        objectMapper.registerModule(module);
        
        return objectMapper;
    }
}
