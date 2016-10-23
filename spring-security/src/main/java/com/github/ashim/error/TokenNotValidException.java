package com.github.ashim.error;

import org.springframework.http.HttpStatus;

public class TokenNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus status;

	public TokenNotValidException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
