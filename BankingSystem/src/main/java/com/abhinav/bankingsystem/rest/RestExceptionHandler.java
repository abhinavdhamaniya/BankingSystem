package com.abhinav.bankingsystem.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler 
{
		//used to convert currentTimeMillis to readable Date
		public String currentTimeMillisToDate(long millis)
		{
	        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
	        Date resultdate = new Date(millis);
	        return sdf.format(resultdate);
		}
		
		//Catches AccountDoesNotExistException and sends StudentErrorResponse (POJO, converted to JSON) as response 
		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handleException(AccountDoesNotExistException exc)
		{
			
			ErrorResponse err= new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), currentTimeMillisToDate(System.currentTimeMillis()));
			return new ResponseEntity<ErrorResponse>(err, HttpStatus.BAD_REQUEST);	
			
		}
		
	
		//Catches all other Exceptions and sends StudentErrorResponse (POJO, converted to JSON) as response 
		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handleException(Exception exc)
		{
			
			ErrorResponse err= new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), currentTimeMillisToDate(System.currentTimeMillis()));
			return new ResponseEntity<ErrorResponse>(err, HttpStatus.BAD_REQUEST);	
			
		}
}
