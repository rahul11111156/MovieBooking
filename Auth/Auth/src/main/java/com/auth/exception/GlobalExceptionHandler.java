package com.auth.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth.response.ErrorResponseDTO;




@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ UserNotFound.class })
	public ErrorResponseDTO userfNotFoundException(Exception exception, HttpServletRequest request) {
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}




}
