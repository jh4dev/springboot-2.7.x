package com.amorepacific.iris.user.portal.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.amorepacific.iris.user.portal.api.TestController;
import com.amorepacific.iris.user.portal.common.constants.ErrorCode;
import com.amorepacific.iris.user.portal.common.exception.BusinessException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 롯데제과 SFA Converting Utility
 * </br> JSON(with Jackson) and JAVA Collection
 * @update 2023-03-13
 * @author jhahn
 * */
@Slf4j
@Component
public class ConvertUtil {
	
	private ConvertUtil() {
		
	}

	private static ObjectMapper objectMapper;

	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		ConvertUtil.objectMapper = objectMapper;
	}
	
	public static void logTest() {
		Package currentPackage = ConvertUtil.class.getPackage();
        String packageName = currentPackage != null ? currentPackage.getName() : "default package";

		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>> Current pacakge : {}", packageName);
	}
	
	/**
	 * @method objectToJsonString
	 * @description Pojo to Json format String
	 * @throws BusinessException 
	 * */
	public static String objectToJsonString(Object obj) throws BusinessException {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(ErrorCode.CONVERT_EXCEPTION_ERROR);
		}
	}
	
	/**
	 * @method anyObjectToJsonNode
	 * @description Any Object To JsonNode
	 * @throws BusinessException 
	 * */
	public static JsonNode objectToJsonNode(Object obj) throws BusinessException {
		try {
			return objectMapper.valueToTree(obj);			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(ErrorCode.CONVERT_EXCEPTION_ERROR);
		}
	}
	
	/**
	 * @method jsonStringToObject
	 * @description jsonString to POJO
	 * @throws BusinessException 
	 * */
	public static <T> T jsonStringToObject(String jsonStr, Class<T> clazz) throws BusinessException {
		try {
			return (T)objectMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(ErrorCode.CONVERT_EXCEPTION_ERROR);
		}
	}
	
	/**
	 * @method jsonNodeToObject
	 * @description JsonNode to POJO
	 * @throws BusinessException 
	 * */
	public static <T> T jsonNodeToObject(JsonNode jsonNode, Class<T> clazz) throws BusinessException {
		try {
			if(jsonNode == null || jsonNode.isNull() || jsonNode.isMissingNode()) {return clazz.getDeclaredConstructor().newInstance();}
			return objectMapper.convertValue(jsonNode, clazz);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(ErrorCode.CONVERT_EXCEPTION_ERROR);
		}
	}
	

	/**
	 * @method makeJsonNodeToArrayList
	 * @description JsonNode to ArrayList<T>
	 * @throws BusinessException 
	 * */
	public static <T> List<T> jsonNodeToArrayList(JsonNode jn, Class<T> clazz) throws BusinessException {
		try {
			
			if(jn == null || jn.isNull() || jn.isMissingNode()) {return new ArrayList<T>();}
			
			JavaType type = objectMapper.getTypeFactory().
					constructCollectionType(List.class, clazz);
			
			ArrayList<T> list = objectMapper.readValue(jn.toString(),  type);
			return list;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(ErrorCode.CONVERT_EXCEPTION_ERROR);
		}
	}
	
	/**
	 * @method objectToMap
	 * @description Pojo to Map
	 * @throws BusinessException 
	 * */
	public static Map<Object, Object> objectToMap(Object obj) throws BusinessException {
		try {
			return jsonStringToObject(objectToJsonString(obj), Map.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(ErrorCode.CONVERT_EXCEPTION_ERROR);
		}
	}
	
	/**
	 * @method mapToPojo
	 * @description Map to POJO
	 * @throws BusinessException 
	 * */
	public static <T> T mapToPojo(Map<String, Object> map, Class<T> clazz) throws BusinessException {
		try {
			 return objectMapper.convertValue(map, clazz);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(ErrorCode.CONVERT_EXCEPTION_ERROR);
		}
	}
	
}
