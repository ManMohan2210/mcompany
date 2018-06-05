package com.mcompany.coupan.dtos;

public class EmptyDataModel {

	private boolean error;
	private boolean success;
	private String message;
	private String type;
	private Class className;
	private Object responseObject;


	public int statusCode;

	public EmptyDataModel() {
		error = true;
		message = "null";
	}
	
	@Override
	public String toString() {
		if (error)
			return "[Error] " + message;
		return "[Success] " + message;
	}

	public Class getClassName() {
		return className;
	}

	public void setClassName(Class className) {
		this.className = className;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
