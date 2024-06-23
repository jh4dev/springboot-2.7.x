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
     * @description Ä¿½ºÅÒ ObjectMapper create
     * 	<br/>	1) DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
     *  <br/> 	- ¿ªÁ÷·ÄÈ­½Ã, ¼±¾ðµÇÁö ¾ÊÀº ¼Ó¼ºÀÌ ÀÖ´Â°æ¿ì ¿¡·¯ ¹ß»ýÇÏÁö ¾Êµµ·Ï ¼³Á¤
     *  <br/>	
     *  <br/>	2) BigDecimalSerializer
     *  <br/>	- Á÷·ÄÈ­ ½Ã, BigDecimal to String µÇµµ·Ï ¼³Á¤
     *  
     * */
    @Bean
    ObjectMapper objectMapper() {
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
