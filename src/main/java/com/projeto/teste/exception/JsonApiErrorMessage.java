package com.projeto.teste.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class JsonApiErrorMessage extends DefaultErrorAttributes {

	private static Exception erro;

	@Override
	public Map<String, Object> getErrorAttributes(final WebRequest webRequest, ErrorAttributeOptions options) {

		@SuppressWarnings("deprecation")
		final Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest,
				options.isIncluded(Include.BINDING_ERRORS));

		final Map<String, Object> jsonApiErrorAttributes = new LinkedHashMap<>();
		
		jsonApiErrorAttributes.put("timestamp", errorAttributes.get("timestamp"));
		jsonApiErrorAttributes.put("status", errorAttributes.get("status"));
		jsonApiErrorAttributes.put("source", errorAttributes.get("path"));
		jsonApiErrorAttributes.put("title", errorAttributes.get("error"));
		jsonApiErrorAttributes.put("detail", erro.getMessage());

		return jsonApiErrorAttributes;
	}

	public Exception getErro() {
		return erro;
	}

	public void setErro(Exception erro) {
		this.erro = erro;
	}

}