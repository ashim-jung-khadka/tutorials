package com.github.ashim.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.error.TokenNotValidException;
import com.github.ashim.security.common.TokenUtils;
import com.github.ashim.security.model.AuthRequest;
import com.github.ashim.security.model.AuthResponse;
import com.github.ashim.security.model.UserCredential;

@RestController
@RequestMapping("${route.authentication}")
public class AuthenticationController {

	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(this.getClass());

	@Value("${token.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<?> authenticationRequest(@RequestBody AuthRequest authRequest, Device device)
			throws AuthenticationException {

		// Perform the authentication
		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-authentication so we can generate token
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequest.getUsername());
		String token = this.tokenUtils.generateToken(userDetails, device);

		// Return the token
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@RequestMapping(value = "${route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
		String token = request.getHeader(this.tokenHeader);

		if (token == null) {
			throw new TokenNotValidException(HttpStatus.BAD_REQUEST, "request not valid");
		}

		String username = this.tokenUtils.getUsernameFromToken(token);
		UserCredential user = (UserCredential) this.userDetailsService.loadUserByUsername(username);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
			String refreshedToken = this.tokenUtils.refreshToken(token);
			return ResponseEntity.ok(new AuthResponse(refreshedToken));

		} else {
			throw new TokenNotValidException(HttpStatus.FORBIDDEN, "Access is denied");
		}
	}

}
