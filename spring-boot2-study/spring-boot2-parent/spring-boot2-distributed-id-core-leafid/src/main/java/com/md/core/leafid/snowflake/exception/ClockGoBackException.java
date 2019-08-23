package com.md.core.leafid.snowflake.exception;

public class ClockGoBackException extends RuntimeException {
	private static final long serialVersionUID = -2021330616975162738L;

	public ClockGoBackException(String message) {
        super(message);
    }
}
