package com.github.ashim.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.persistence.entity.User;
import com.github.ashim.provider.service.UserService;

@RestController
@RequestMapping("${route.users}")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("@securityService.hasProtectedAccess('USER')")
	public ResponseEntity<List<User>> getAllUsers() {

		return ResponseEntity.ok().body(userService.getAllUsers());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") Integer id) {

		return ResponseEntity.ok().body(userService.getUserById(id));
	}

}