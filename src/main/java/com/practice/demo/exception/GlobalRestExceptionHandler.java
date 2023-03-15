package com.practice.demo.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.practice.demo.model.ApiError;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(value = { PracticeDemoRunTimeException.class })
	protected ResponseEntity<Object> handlePracticeDemoRuntimeException(PracticeDemoRunTimeException ex,
			WebRequest request) {
		String errodId = UUID.randomUUID().toString();
		ApiError apiError = new ApiError(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getLocalizedMessage(), errodId);
		log.error("inside PracticeDemoRunTimeException:", ex.getLocalizedMessage());
		log.error("inside PracticeDemoRunTimeException: errorID" + errodId);
		return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		String errodId = UUID.randomUUID().toString();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
			log.error("CVE" + violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}
		log.error("inside handleConstraintViolation: errorID" + errodId);
		ApiError apiError = new ApiError(String.valueOf(HttpStatus.BAD_REQUEST.value()),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getConstraintViolations().toString(), errodId);
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> userNotFoundException(ResourceNotFoundException ex) {

		String errodId = UUID.randomUUID().toString();
		ApiError apiError = new ApiError(String.valueOf(HttpStatus.NOT_FOUND.value()),
				HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getLocalizedMessage(), errodId);

		return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);

	}
}