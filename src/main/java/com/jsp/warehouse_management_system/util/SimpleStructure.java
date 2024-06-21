package com.jsp.warehouse_management_system.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class SimpleStructure<T> {

	int status;
	String message;
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public SimpleStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}
	public SimpleStructure<T> setMesssage(String message) {
		this.message = message;
		return this;
	}
	

}
