package com.demo.edts.generic;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	private static final ObjectMapper modelMapper = new ObjectMapper();
	
	public static Object instantiateObjectFromJson(String data, String clazzName) throws JsonParseException, JsonMappingException, ClassNotFoundException, IOException {
		return modelMapper.readValue(data, Class.forName(clazzName));
	}
	
	public static Object instantiateClassFromName(String clazzName, String data) throws InstantiationException, IllegalAccessException, ClassNotFoundException, JsonParseException, JsonMappingException, IOException {
		
		if(StringUtils.isEmpty(data)){
			return Class.forName(clazzName).newInstance();
		} else {
			return instantiateObjectFromJson(data, clazzName);
		}
	}
	
}
