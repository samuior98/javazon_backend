package com.generation.javazon.controller.util;

import java.util.NoSuchElementException;

import org.hibernate.HibernateException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GestoreEccezioni
{
	@ExceptionHandler(NoSuchElementException.class)
	public  ResponseEntity <String> handleNoSuchElementException(NoSuchElementException e)
	{
		return new ResponseEntity <>(e.getMessage(), HttpStatus.NOT_FOUND);
		//questo metodo da la response nel caso in qui questa eccezione si verifichi in qualsiasi punto di classi che lo importano
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity <String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
	{
		return new ResponseEntity <>("Il parametro in ingresso è errato", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public  ResponseEntity <String> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e)
	{
		return new ResponseEntity <>("Database fuori uso", HttpStatus.SERVICE_UNAVAILABLE);

	}


	@ExceptionHandler(InvalidEntityException.class)
	public  ResponseEntity <String> handleInvalidEntityException(InvalidEntityException e)
	{
		return new ResponseEntity <>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public  ResponseEntity <String> handleMissingPathVariableException(MissingPathVariableException e)
	{
		return new ResponseEntity <>("Inserire un parametro corretto", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HibernateException.class)
	public  ResponseEntity <String> handleHibernateException(HibernateException e)
	{
		return new ResponseEntity <>("Controlla il JSON", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ForbiddenOperationException.class)
	public ResponseEntity<String> handleForbiddenOperationException(ForbiddenOperationException e)
	{
		return new ResponseEntity <>(e.getMessage(), HttpStatus.FORBIDDEN);
	}
}
