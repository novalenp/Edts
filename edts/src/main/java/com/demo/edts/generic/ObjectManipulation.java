package com.demo.edts.generic;

import java.io.IOException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ObjectManipulation {
	public static Object instantiateClassFromName(String clazzName, String data) throws InstantiationException, IllegalAccessException, ClassNotFoundException, JsonParseException, JsonMappingException, IOException {
		
		if(null==data && StringUtils.isEmpty(data)){
			return Class.forName(clazzName).newInstance();
		} else {
			return JSONUtil.instantiateObjectFromJson(data, clazzName);
		}
	}
	
	public static Object evaluateOptional(Object obj) {
		if (obj instanceof java.util.Optional) {
			return ((Optional<?>) obj).orElse(null);
		} else {
			return obj;
		}
	}

}
