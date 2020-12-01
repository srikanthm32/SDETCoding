package com.states.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StateNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4068048520130713592L;

	public StateNotFoundException() {
		super();
	}

	public StateNotFoundException(String message) {
		super(message);
	}

	public StateNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
