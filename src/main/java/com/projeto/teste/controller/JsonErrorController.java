package com.projeto.teste.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class JsonErrorController extends AbstractErrorController {

	public JsonErrorController(final ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> error(final HttpServletRequest request) {
		final Map<String, Object> body = this.getErrorAttributes(request, true);
		final HttpStatus status = this.getStatus(request);
		return new ResponseEntity<>(body, status);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	
}