package com.projeto.teste.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.TokenExpiredException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler implements ErrorController {

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public ResponseEntity<?> handleAnyException(Exception e) {

		if (e instanceof BadCredentialsException) {
			return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST.value(), "Missing fields"),
					HttpStatus.BAD_REQUEST);
		}

		if (e instanceof DataIntegrityViolationException) {
			return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST.value(), "E-mail already exists"),
					HttpStatus.BAD_REQUEST);
		}

		if (e instanceof NullPointerException) {
			return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST.value(), "Invalid e-mail or password"),
					HttpStatus.BAD_REQUEST);
		}
		
		if (e instanceof TokenExpiredException) {
			return new ResponseEntity<>(new Error(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"),
					HttpStatus.UNAUTHORIZED);
		}
		
		if (e instanceof NotFound) {
			return new ResponseEntity<>(new Error(HttpStatus.NOT_FOUND.value(), "Page not found"),
					HttpStatus.NOT_FOUND);
		}


		return new ResponseEntity<>(new Error(HttpStatus.UNAUTHORIZED.value(), "unauthorized or empty base"),
				HttpStatus.UNAUTHORIZED);
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

}