package com.github.ashim.test.model.json.request;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ashim.security.model.AuthRequest;

public class AuthRequestTest {

	private final String USERNAME = "username";
	private final String PASSWORD = "password";

	@Test
	public void callingAuthRequestConstructorWithoutParametersCreatesExpectedObject() {
		AuthRequest authRequest = new AuthRequest();

		assertNull(authRequest.getUsername());
		assertNull(authRequest.getPassword());
	}

	@Test
	public void callingAuthRequestConstructorWithParametersCreatesExpectedObject() {
		AuthRequest authRequest = new AuthRequest(USERNAME, PASSWORD);

		assertThat(authRequest.getUsername(), is(USERNAME));
		assertThat(authRequest.getPassword(), is(PASSWORD));
	}

	@Test
	public void callingAuthRequestGettersAndSettersReturnsExpectedObjects() {
		AuthRequest authRequest = new AuthRequest();

		authRequest.setUsername(USERNAME);
		authRequest.setPassword(PASSWORD);

		assertThat(authRequest.getUsername(), is(USERNAME));
		assertThat(authRequest.getPassword(), is(PASSWORD));
	}

}
