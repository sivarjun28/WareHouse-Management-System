package com.jsp.warehouse_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseNotFoundByIdException extends RuntimeException{

	private String message;
}
