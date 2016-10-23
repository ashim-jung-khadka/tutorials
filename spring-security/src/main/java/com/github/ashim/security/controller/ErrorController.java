package com.github.ashim.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.error.ApiError;

@RestController
@RequestMapping("${route.error}")
public class ErrorController {

	@RequestMapping(value = "${route.error.unauthorized}", method = RequestMethod.GET)
	public ResponseEntity<ApiError> unauthorizedPageGet(@RequestParam String message) {

		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, message, "access unauthorized");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);

	}

	@RequestMapping(value = "${route.error.unauthorized}", method = RequestMethod.POST)
	public ResponseEntity<ApiError> unauthorizedPagePost(@RequestParam String message) {

		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, message, "access unauthorized");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);

	}

	@RequestMapping(value = "${route.error.forbidden}", method = RequestMethod.GET)
	public ResponseEntity<ApiError> forbiddenPageGet(@RequestParam String message) {

		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, message, "access forbidden");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);

	}

	@RequestMapping(value = "${route.error.forbidden}", method = RequestMethod.POST)
	public ResponseEntity<ApiError> forbiddenPagePost(@RequestParam String message) {

		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, message, "access forbidden");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);

	}

}
