package com.github.ashim.security.model;

import com.github.ashim.security.common.ModelBase;

public class AuthResponse extends ModelBase {

	private static final long serialVersionUID = -6624726180748515507L;

	private String token;

	public AuthResponse() {
		super();
	}

	public AuthResponse(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
