package com.projeto.teste.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler implements ErrorController{

	@ExceptionHandler(value = { SignatureVerificationException.class })
	@ResponseBody
	public ResponseEntity<DefaultError> handlerTeste(SignatureVerificationException e) {
		DefaultError erro = new DefaultError();
		return new ResponseEntity<>(erro, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = { TokenExpiredException.class })
	@ResponseBody
	public ResponseEntity<DefaultError> handlerTeste2(TokenExpiredException e) {
		DefaultError erro = new DefaultError();
		return new ResponseEntity<>(erro, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public ResponseEntity<Object> handleAnyException(Exception e) {

		return new ResponseEntity<>("erro teste", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

}