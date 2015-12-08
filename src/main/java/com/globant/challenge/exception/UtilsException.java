package com.globant.challenge.exception;

public class UtilsException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 9086161967295748677L;

	public UtilsException() {
	}

	public UtilsException(final String message) {
		super(message);
	}

	public UtilsException(final Throwable cause) {
		super(cause);
	}

	public UtilsException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
