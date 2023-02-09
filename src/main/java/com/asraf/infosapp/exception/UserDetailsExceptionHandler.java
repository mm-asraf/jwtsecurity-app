package com.asraf.infosapp.exception;

public class UserDetailsExceptionHandler extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public UserDetailsExceptionHandler(String message) {
			super(message);
		}
}
