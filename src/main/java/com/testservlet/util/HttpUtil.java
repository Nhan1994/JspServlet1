package com.testservlet.util;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
	private String value;
	
	public HttpUtil(String value) {
		this.value = value;
	}
	
	public <T> T toModel(Class<T> clazz) {
		try {
			return new ObjectMapper().readValue(value, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {		
			e.printStackTrace();
			return null;
		} catch (IOException e) {	
			e.printStackTrace();
			return null;
		}
	}
	
	public static HttpUtil of (BufferedReader br) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		return new HttpUtil(sb.toString());
	}

}
