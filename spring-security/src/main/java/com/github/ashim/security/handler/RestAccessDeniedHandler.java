package com.github.ashim.security.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

	@Value("${route.error}")
	private String errorPage;

	@Value("${route.error.forbidden}")
	private String forbiddenPage;

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		String parameter = "?message=" + accessDeniedException.getMessage();
		String path = errorPage + forbiddenPage + parameter;

		RequestDispatcher rd = httpServletRequest.getRequestDispatcher(path);
		rd.forward(httpServletRequest, httpServletResponse);

	}

}