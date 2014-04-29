package com.smartbear.sso;

public class SSOException extends Exception {

	private static final long serialVersionUID = -3804736057054669087L;

	public SSOException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SSOException(String msg) {
		super(msg);
	}
}
