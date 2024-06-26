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

import com.jsp.warehouse_management_system.exception.AddressNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.AdminNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.ClientNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.IllegalOperationException;
import com.jsp.warehouse_management_system.exception.InventoryNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.StorageNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.StorageTypeNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.UsernameNotFoundException;
import com.jsp.warehouse_management_system.exception.WareHouseNotFoundByCityException;
import com.jsp.warehouse_management_system.exception.WareHouseNotFoundByIdException;

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
						.setMessage("invalid input")
						.setRootCause(allErrors));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> illegalOperationException(IllegalOperationException ioe){
		return errorResponse(HttpStatus.NOT_FOUND, ioe.getMessage(), "Super admin already exists");

	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> wareHouseNotFoundByIdException(WareHouseNotFoundByIdException exe){
		return errorResponse(HttpStatus.NOT_FOUND, exe.getMessage(), "Ware HOuse is Not Present");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> adminNotFoundByIdException(AdminNotFoundByIdException exec){
		return errorResponse(HttpStatus.NOT_FOUND, exec.getMessage(), "Admin Not Found Id Exception");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> addressNotFoundByIdException(AddressNotFoundByIdException aexc){
		return errorResponse(HttpStatus.NOT_FOUND, aexc.getMessage(), "Admin Not Found Id Exception");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> storageNotFoundByException(StorageNotFoundByIdException exe){
		return errorResponse(HttpStatus.NOT_FOUND, exe.getMessage(),"Storage is not found by Id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> wareHouseNotFoundByCityException(WareHouseNotFoundByCityException exe){
		return errorResponse(HttpStatus.NOT_FOUND, exe.getMessage(),"WareHouse Not found By City Name");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> clientNotFoundByIdException(ClientNotFoundByIdException exe){
		return errorResponse(HttpStatus.NOT_FOUND, exe.getMessage(),"Client not Found based on Id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> storageTypeNotFoundByIdException(StorageTypeNotFoundByIdException exe){
		return errorResponse(HttpStatus.NOT_FOUND, exe.getMessage(),"storageType not found");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> usernameNotFoundException(UsernameNotFoundException exception){
		return errorResponse(HttpStatus.NOT_FOUND, exception.getMessage(),"user not found");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> inventoryNotFoundByIdException(InventoryNotFoundByIdException exception){
		return errorResponse(HttpStatus.NOT_FOUND, exception.getMessage(),"inventory not found based on Id given");
	}
	
}
