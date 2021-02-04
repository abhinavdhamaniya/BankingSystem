package com.abhinav.bankingsystem.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler 
{
	
		//Catches all other Exceptions and sends StudentErrorResponse (POJO, converted to JSON) as response 
		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handleException(Exception exc)
		{
			
			ErrorResponse err= new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
			return new ResponseEntity<ErrorResponse>(err, HttpStatus.BAD_REQUEST);	
			
		}
}
