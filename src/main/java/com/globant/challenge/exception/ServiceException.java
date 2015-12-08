package com.globant.challenge.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 3117895032748882898L;

	public ServiceException() {
	}

	public ServiceException(final String message) {
		super(message);
	}

	public ServiceException(final Throwable cause) {
		super(cause);
	}

	public ServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
