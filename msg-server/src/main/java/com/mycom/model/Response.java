package com.mycom.model;

public class Response<T> {
	
	private int code;
	private String errorMessage;
	private T response;
	
	public Response(int code, T response){
		this.code = code;
		this.response = response;
	}
	
	public Response(int code, String errorMessage){
		this.code = code;
		this.errorMessage = errorMessage;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	
	
}
