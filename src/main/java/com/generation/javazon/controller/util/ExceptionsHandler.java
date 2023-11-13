package com.generation.javazon.controller.util;

import java.util.NoSuchElementException;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionsHandler
{
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public ResponseEntity <String> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e)
	{
		return new ResponseEntity <>("Database non raggiungibile", HttpStatus.SERVICE_UNAVAILABLE);
	}


	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity <String> handleNoSuchElementException(NoSuchElementException e)
	{
		return new ResponseEntity <>(e.getMessage(), HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity <String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
	{
		return new ResponseEntity <>("Parametro non valido", HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity <String> handleInvalidEntityException(InvalidEntityException e)
	{
		return new ResponseEntity <>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}


	@ExceptionHandler(HttpMessageNotReadableException.class)
	public  ResponseEntity <String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e)
	{
		return new ResponseEntity <>("Formato JSON non valido", HttpStatus.BAD_REQUEST);
	}


}
