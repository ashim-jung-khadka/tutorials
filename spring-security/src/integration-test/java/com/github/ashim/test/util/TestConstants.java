package com.github.ashim.test.util;

import com.github.ashim.security.model.AuthRequest;

public class TestConstants {

	public static final AuthRequest USER_AUTH_REQUEST = new AuthRequest("user", "password");
	public static final AuthRequest ADMIN_AUTH_REQUEST = new AuthRequest("admin", "admin");
	public static final AuthRequest EXPIRED_AUTH_REQUEST = new AuthRequest("expired", "expired");
	public static final AuthRequest INVALID_AUTH_REQUEST = new AuthRequest("user", "abc123");

	public static final String INVALID_TOKEN = "token";

}