package com.alerta.dc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

@Service
public class Response {
	public ResponseEntity<?> message(String message, HttpStatus status){
		Map<String, String> response = new HashMap<>();
		response.put("message", message);
//		response.put("status", status.toString());
		return new ResponseEntity<>(response, status);
	}
	
	public ResponseEntity<?> map(Map<?,?> response, HttpStatus status){
		return new ResponseEntity<>(response, status);
	}
	
	public ResponseEntity<?> list(List<?> list, HttpStatus status){
		Map<String, List<?>> response = new HashMap<>();
		response.put("message", list);
		return new ResponseEntity<>(response, status);
	}

	public ResponseEntity<Object> objectMap(Object object, HttpStatus status){
		Map<String, Object> response = new HashMap<>();
		response.put("message", object);
		return new ResponseEntity<Object>(response, status);
	}
	
	public <T> ResponseEntity<?> object(@Nullable T body, HttpStatus status){
		return new ResponseEntity<>(body, status);
	}
}
