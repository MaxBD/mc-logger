package com.bd.logger.service.exceptions;

/**
 * Created by semyon on 5/15/17.
 */
public class NullOrEmptyException extends Exception {
	public NullOrEmptyException() {
	}

	public NullOrEmptyException(String message) {
		super(message);
	}

	public NullOrEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullOrEmptyException(Throwable cause) {
		super(cause);
	}

	public NullOrEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

