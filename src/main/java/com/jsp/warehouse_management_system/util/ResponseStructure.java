package com.jsp.warehouse_management_system.util;

public class ResponseStructure<T> {

	
	private int Status;
	private String message;
	private T data;
	public int getStatus() {
		return Status;
	}
	public String getMessage() {
		return message;
	}
	public T getData() {
		return data;
	}
	public ResponseStructure<T> setStatus(int status) {
		Status = status;
		return this;
	}
	public ResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	public ResponseStructure<T> setData(T data) {
		this.data = data;
		return this;
	}
	
	
}
