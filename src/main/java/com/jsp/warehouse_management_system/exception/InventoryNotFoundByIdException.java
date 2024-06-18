package com.jsp.warehouse_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryNotFoundByIdException  extends RuntimeException{

	private String message;
}
