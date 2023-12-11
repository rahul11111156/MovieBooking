package com.userprofile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CustomExceptionHandler extends RuntimeException {

    public CustomExceptionHandler(String string) {
		// TODO Auto-generated constructor stub
    	super(string);
	}
}

