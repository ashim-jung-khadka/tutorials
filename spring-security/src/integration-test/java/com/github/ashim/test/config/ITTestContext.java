package com.github.ashim.test.config;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.ashim.config.ApplicationConfiguration;
import com.github.ashim.security.config.SecurityConfiguration;
import com.github.ashim.security.model.AuthRequest;
import com.github.ashim.security.model.AuthResponse;
import com.github.ashim.test.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class, SecurityConfiguration.class })
@WebAppConfiguration
public class ITTestContext {

	protected final Logger logger = Logger.getLogger(this.getClass());

	protected MockMvc mockMvc;

	protected String authenticationToken;

	@Value("${route.authentication}")
	protected String authenticationRoute;

	@Resource
	protected WebApplicationContext webApplicationContext;

	@Autowired
	private Filter springSecurityFilterChain;

	@Before
	public void globalSetUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilters(springSecurityFilterChain)
				.build();
	}

	protected void requestForAuthenticationToken(AuthRequest authRequest) {

		try {
			String result = mockMvc.perform(post(authenticationRoute)
					.contentType(TestUtil.APPLICATION_JSON_UTF8)
					.content(TestUtil.toJSON(authRequest)))
					.andReturn()
					.getResponse()
					.getContentAsString();

			AuthResponse authResponse = TestUtil.fromJSON(result, AuthResponse.class);
			authenticationToken = authResponse.getToken();

		} catch (Exception e) {
			logger.debug("error", e);
			fail("makeAuthenticationRequestForToken not working");
		}
	}

}
