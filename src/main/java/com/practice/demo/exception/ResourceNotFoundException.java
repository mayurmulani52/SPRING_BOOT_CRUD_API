package com.practice.demo.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {

		super(message);

	}

	public ResourceNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}
}