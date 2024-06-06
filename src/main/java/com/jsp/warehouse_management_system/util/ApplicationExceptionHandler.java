package com.jsp.warehouse_management_system.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.warehouse_management_system.exception.IllegalOperationException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	@SuppressWarnings("unused")
	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String message, 
			String rootCause){
		return ResponseEntity
				.status(status)
				.body(new ErrorStructure<String>()
						.setStatus(status.value())
						.setMessage(message)
						.setRootCause(rootCause));
	}


	@ExceptionHandler
	public ResponseEntity<ErrorStructure<Map<String,String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		List<ObjectError> errors =  ex.getAllErrors();

		Map<String, String> allErrors = new HashMap<String, String>();
		errors.forEach(error ->{
			FieldError fieldError = (FieldError) error;
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			allErrors.put(field, message);
		});
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorStructure<Map<String,String>>()
						.setStatus(HttpStatus.BAD_REQUEST.value())
						.setMessage("ivalid input")
						.setRootCause(allErrors));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> illegalOperationException(IllegalOperationException ioe){
		return errorResponse(HttpStatus.NOT_FOUND, ioe.getMessage(), "Super admin is not Present");
 
	}

}
