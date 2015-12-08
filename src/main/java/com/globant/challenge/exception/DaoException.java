package com.globant.challenge.exception;

public class DaoException extends Exception{

	private static final long serialVersionUID = -3700054790308522583L;

	public DaoException() {
	}

	public DaoException(final String message) {
		super(message);
	}

	public DaoException(final Throwable cause) {
		super(cause);
	}

	public DaoException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
