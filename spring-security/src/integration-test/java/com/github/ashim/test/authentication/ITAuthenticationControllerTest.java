package com.github.ashim.test.authentication;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.github.ashim.security.common.TokenUtils;
import com.github.ashim.security.model.AuthRequest;
import com.github.ashim.security.model.AuthResponse;
import com.github.ashim.test.config.ITTestContext;
import com.github.ashim.test.util.TestConstants;
import com.github.ashim.test.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class ITAuthenticationControllerTest extends ITTestContext {

	@Value("${route.authentication.refresh}")
	private String refreshRoute;

	@Autowired
	private TokenUtils tokenUtils;

	private AuthRequest authRequest;

	@Before
	public void setUp() {
		refreshRoute = authenticationRoute + refreshRoute;
		authenticationToken = null;
		authRequest = null;
	}

	@Test
	public void requesting_Authentication_With_NoCredentials_Returns_BadRequest() {

		try {
			mockMvc.perform(post(authenticationRoute)
					.contentType(TestUtil.APPLICATION_JSON_UTF8))
					.andExpect(status().isBadRequest());

		} catch (Exception e) {
			logger.debug("error", e);
			fail("Should have returned an HTTP 400: Bad Request status code");
		}
	}

	@Test
	public void requesting_Authentication_With_InvalidCredentials_Returns_Unauthorized() {

		this.authRequest = TestConstants.INVALID_AUTH_REQUEST;

		try {
			mockMvc.perform(post(authenticationRoute)
					.contentType(TestUtil.APPLICATION_JSON_UTF8)
					.content(TestUtil.toJSON(authRequest)))
					.andExpect(status().isUnauthorized())
					.andExpect(jsonPath("$.status", is(HttpStatus.UNAUTHORIZED.name())))
					.andExpect(jsonPath("$.message", is("Bad credentials")));

		} catch (Exception e) {
			logger.debug("error", e);
			fail("Should have returned an HTTP 401: Unauthorized status code");
		}
	}

	@Test
	public void requesting_AuthorizationToken_With_ValidCredentials_Returns_Ok() throws Exception {

		this.authRequest = TestConstants.USER_AUTH_REQUEST;

		String result = mockMvc.perform(post(authenticationRoute)
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.toJSON(authRequest)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		AuthResponse authResponse = TestUtil.fromJSON(result, AuthResponse.class);

		try {
			assertThat(this.tokenUtils.getUsernameFromToken(authResponse.getToken()), is(authRequest.getUsername()));

		} catch (Exception e) {
			logger.debug("error", e);
			fail("Should have returned expected username from token");
		}
	}

	@Test
	public void requesting_RefreshToken_With_NoAuthorizationToken_Returns_BadRequest() throws Exception {

		try {
			mockMvc.perform(get(refreshRoute)
					.contentType(TestUtil.APPLICATION_JSON_UTF8))
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
					.andExpect(jsonPath("$.message", is("request not valid")));

		} catch (Exception e) {
			logger.debug("error", e);
			fail("Should have returned an HTTP 400: Bad Request status code");
		}
	}

	@Test
	public void requesting_RefreshToken_With_ExpiredToken_Returns_Forbidden() {

		this.requestForAuthenticationToken(TestConstants.EXPIRED_AUTH_REQUEST);

		try {
			mockMvc.perform(get(refreshRoute)
					.contentType(TestUtil.APPLICATION_JSON_UTF8)
					.header("Authorization", authenticationToken))
					.andExpect(status().isForbidden())
					.andExpect(jsonPath("$.status", is(HttpStatus.FORBIDDEN.name())))
					.andExpect(jsonPath("$.message", is("Access is denied")));

		} catch (Exception e) {
			logger.debug("error", e);
			fail("Should have returned an HTTP 403: Forbidden status code");
		}
	}

	@Test
	public void requesting_Refresh_With_InvalidToken_Returns_Unauthorized() throws Exception {

		this.authenticationToken = TestConstants.INVALID_TOKEN;

		try {
			mockMvc.perform(get(refreshRoute)
					.contentType(TestUtil.APPLICATION_JSON_UTF8)
					.header("Authorization", authenticationToken))
					.andExpect(status().isUnauthorized())
					.andExpect(jsonPath("$.status", is(HttpStatus.UNAUTHORIZED.name())))
					.andExpect(jsonPath("$.message", is("Unauthorized access")));

		} catch (Exception e) {
			logger.debug("error", e);
			fail("Should have returned an HTTP 401: Unauthorized status code");
		}
	}

	@Test
	public void requesting_RefreshToken_With_ValidToken_Returns_Ok() throws Exception {

		this.authRequest = TestConstants.USER_AUTH_REQUEST;
		this.requestForAuthenticationToken(authRequest);

		String result = mockMvc.perform(get(refreshRoute)
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.header("Authorization", authenticationToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token", notNullValue()))
				.andReturn().getResponse()
				.getContentAsString();

		try {
			AuthResponse authResponse = TestUtil.fromJSON(result, AuthResponse.class);
			assertThat(this.tokenUtils.getUsernameFromToken(authResponse.getToken()), is(authRequest.getUsername()));

		} catch (Exception e) {
			logger.debug("error", e);
			fail("Should have returned expected username from token");
		}
	}

	// Sample
	@SuppressWarnings("unused")
	private void sample() throws Exception {
		AuthRequest ADMIN_AUTH_REQUEST = new AuthRequest("admin", "admin");

		MvcResult result = mockMvc.perform(post("/auth")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.toJSON(ADMIN_AUTH_REQUEST)))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		AuthResponse authResponse = TestUtil.fromJSON(content,
				AuthResponse.class);
		System.out.println(authResponse);
	}

}
