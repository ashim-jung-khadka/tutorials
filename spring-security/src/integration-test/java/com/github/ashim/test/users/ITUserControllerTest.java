package com.github.ashim.test.users;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import com.github.ashim.test.config.ITTestContext;
import com.github.ashim.test.util.TestConstants;
import com.github.ashim.test.util.TestUtil;

public class ITUserControllerTest extends ITTestContext {

	@Value("${route.users}")
	private String usersRoute;

	@Test
	public void requesting_Users_With_NoAuthorizationToken_Returns_Unauthorized() throws Exception {

		try {
			mockMvc.perform(get(usersRoute)
					.contentType(TestUtil.APPLICATION_JSON_UTF8))
					.andExpect(forwardedUrl(
							"/error/unauthorized?message=Full authentication is required to access this resource"));

		} catch (Exception ex) {
			logger.debug("error", ex);
			fail("Should have returned an HTTP 401: Unauthorized status code");
		}
	}

	@Test
	public void requesting_Users_With_InvalidToken_Returns_Unauthorized() throws Exception {

		this.authenticationToken = TestConstants.INVALID_TOKEN;

		try {
			mockMvc.perform(get(usersRoute)
					.contentType(TestUtil.APPLICATION_JSON_UTF8)
					.header("Authorization", authenticationToken))
					.andExpect(forwardedUrl(
							"/error/unauthorized?message=Full authentication is required to access this resource"));

		} catch (Exception ex) {
			logger.debug("error", ex);
			fail("Should have returned an HTTP 401: Unauthorized status code");
		}
	}

	@Test
	public void requesting_Users_With_InvalidPrivilegeCredentials_Returns_Forbidden()
			throws Exception {

		requestForAuthenticationToken(TestConstants.USER_AUTH_REQUEST);

		try {
			mockMvc.perform(get(usersRoute + "/1")
					.contentType(TestUtil.APPLICATION_JSON_UTF8)
					.header("Authorization", authenticationToken))
					.andExpect(status().isForbidden())
					.andExpect(jsonPath("$.status", is(HttpStatus.FORBIDDEN.name())))
					.andExpect(jsonPath("$.message", is("Access is denied")));

		} catch (Exception ex) {
			logger.debug("error", ex);
			fail("Should have returned an HTTP 403: Forbidden status code");
		}

	}

	@Test
	public void requesting_Users_With_AuthorizedCredentials_Returns_Ok()
			throws Exception {

		requestForAuthenticationToken(TestConstants.USER_AUTH_REQUEST);

		try {
			mockMvc.perform(get(usersRoute)
					.contentType(TestUtil.APPLICATION_JSON_UTF8)
					.header("Authorization", authenticationToken))
					.andExpect(status().isOk());

		} catch (Exception ex) {
			logger.debug("error", ex);
			fail("Should have returned an HTTP 200: Ok status code");
		}

	}

	@Test
	public void requesting_Users_With_ValidPrivilegeCredentials_Returns_Ok()
			throws Exception {

		requestForAuthenticationToken(TestConstants.ADMIN_AUTH_REQUEST);

		try {
			mockMvc.perform(get(usersRoute + "/1")
					.contentType(TestUtil.APPLICATION_JSON_UTF8)
					.header("Authorization", authenticationToken))
					.andExpect(status().isOk());

		} catch (Exception ex) {
			logger.debug("error", ex);
			fail("Should have returned an HTTP 200: Ok status code");
		}

	}

	// private void initializeStateForMakingValidUsersRequest() {
	// authRequest = TestConstants.ADMIN_AUTH_REQUEST;
	//
	// ResponseEntity<AuthResponse> authResponse = client
	// .postForEntity(TestConstants.getAbsolutePath(authenticationRoute),
	// authRequest, AuthResponse.class);
	//
	// authenticationToken = authResponse.getBody().getToken();
	// }
	//
	// private void initializeStateForMakingInvalidUsersRequest() {
	// authRequest = TestConstants.USER_AUTH_REQUEST;
	//
	// ResponseEntity<AuthResponse> authResponse = client
	// .postForEntity(TestConstants.getAbsolutePath(authenticationRoute),
	// authRequest, AuthResponse.class);
	//
	// authenticationToken = authResponse.getBody().getToken();
	// }
	//
	// private HttpEntity<Object> buildUsersRequestEntity() {
	// return
	// RequestEntityBuilder.buildRequestEntityWithoutBody(authenticationToken);
	// }
	//
	// private HttpEntity<Object>
	// buildUsersRequestEntityWithoutAuthorizationToken() {
	// return
	// RequestEntityBuilder.buildRequestEntityWithoutBodyOrAuthenticationToken();
	// }

}
