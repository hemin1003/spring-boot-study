package com.md.demo.exception;

public class ParamaErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParamaErrorException() {
	}

	public ParamaErrorException(String message) {
		super(message);
	}

}
