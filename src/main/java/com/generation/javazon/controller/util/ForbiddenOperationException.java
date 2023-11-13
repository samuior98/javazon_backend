package com.generation.javazon.controller.util;

@SuppressWarnings("serial")
public class ForbiddenOperationException extends RuntimeException
{
	public ForbiddenOperationException(String m)
	{
		super(m);
	}
}
