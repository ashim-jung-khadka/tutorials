package com.github.ashim.test.model.json.response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ashim.security.model.AuthResponse;

public class AuthResponseTest {

	private final String TOKEN = "token";

	@Test
	public void callingAuthResponseConstructorWithoutParametersCreatesExpectedObject() {
		AuthResponse authResponse = new AuthResponse();

		assertNull(authResponse.getToken());
	}

	@Test
	public void callingAuthResponseConstructorWithParametersCreatesExpectedObject() {
		AuthResponse authResponse = new AuthResponse(TOKEN);

		assertThat(authResponse.getToken(), is(TOKEN));
	}

	@Test
	public void callingAuthResponseGettersAndSettersReturnsExpectedObjects() {
		AuthResponse authResponse = new AuthResponse();

		authResponse.setToken(TOKEN);
		assertThat(authResponse.getToken(), is(TOKEN));
	}

}
