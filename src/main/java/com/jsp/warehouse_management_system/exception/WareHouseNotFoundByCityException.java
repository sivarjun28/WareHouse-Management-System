package com.jsp.warehouse_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WareHouseNotFoundByCityException extends RuntimeException {

	private String message;
}
