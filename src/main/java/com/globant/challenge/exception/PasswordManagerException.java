package com.globant.challenge.exception;

public class PasswordManagerException extends Exception{

	private static final long serialVersionUID = -3700054790308522583L;

	public PasswordManagerException() {
	}

	public PasswordManagerException(final String message) {
		super(message);
	}

	public PasswordManagerException(final Throwable cause) {
		super(cause);
	}

	public PasswordManagerException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
