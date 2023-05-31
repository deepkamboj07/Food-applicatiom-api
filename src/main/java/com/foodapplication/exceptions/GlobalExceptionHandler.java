package com.foodapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorFile> userNotFound(NotFoundException excep)
	{
		ErrorFile err= new ErrorFile();
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setMessage(excep.getMessage());
		err.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorFile> userNotFound(BadRequestException excep)
	{
		ErrorFile err= new ErrorFile();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(excep.getMessage());
		err.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorFile> aceesDenined(UnauthorizedException excep)
	{
		ErrorFile err= new ErrorFile();
		err.setStatus(HttpStatus.FORBIDDEN.value());
		err.setMessage(excep.getMessage());
		err.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(err,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorFile> userNotFound(Exception excep)
	{
		ErrorFile err= new ErrorFile();
		err.setStatus(HttpStatus.BAD_GATEWAY.value());
		err.setMessage(excep.getMessage());
		err.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(err,HttpStatus.BAD_GATEWAY);
	}
}
