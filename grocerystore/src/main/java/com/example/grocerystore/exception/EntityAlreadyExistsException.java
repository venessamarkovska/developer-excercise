package com.example.grocerystore.exception;

public class EntityAlreadyExistsException extends RuntimeException {

	public EntityAlreadyExistsException() {
		super();
	}

	public EntityAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public EntityAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

	public EntityAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
