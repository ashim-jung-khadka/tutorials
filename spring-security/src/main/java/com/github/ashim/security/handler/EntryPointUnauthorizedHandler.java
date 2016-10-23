package com.github.ashim.security.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

	@Value("${route.error}")
	private String errorPage;

	@Value("${route.error.unauthorized}")
	private String unauthorizedPage;

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException authException) throws IOException, ServletException {

		String parameter = "?message=" + authException.getMessage();
		String path = errorPage + unauthorizedPage + parameter;

		RequestDispatcher rd = httpServletRequest.getRequestDispatcher(path);
		rd.forward(httpServletRequest, httpServletResponse);

	}

}