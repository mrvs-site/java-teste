//package com.projeto.teste.exception;
//
//import java.util.Collections;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.function.ServerRequest;
//
//@Component
//public class JsonApiErrorMessage extends DefaultErrorAttributes {
//
//	@Override
//	public Map<String, Object> getErrorAttributes(final WebRequest webRequest, ErrorAttributeOptions options) {
//
//		final Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest,  options.isIncluded(Include.BINDING_ERRORS));
//
//		
//		final Map<String, Object> jsonApiErrorAttributes = new LinkedHashMap<>();
//		jsonApiErrorAttributes.put("timestamp", errorAttributes.get("timestamp"));
//		jsonApiErrorAttributes.put("status", errorAttributes.get("status"));
//		jsonApiErrorAttributes.put("source", Collections.singletonMap("pointer", errorAttributes.get("path")));
//		jsonApiErrorAttributes.put("title", errorAttributes.get("error"));
//		jsonApiErrorAttributes.put("detail", errorAttributes.get("message"));
//
//		return jsonApiErrorAttributes;
//	}
//
//	
//	public Throwable getError(ServerRequest request) {
//		return (Throwable) request.attribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR")
//				.orElseThrow(() -> new IllegalStateException("Missing exception attribute in ServerWebExchange"));
//	}
//	
//}