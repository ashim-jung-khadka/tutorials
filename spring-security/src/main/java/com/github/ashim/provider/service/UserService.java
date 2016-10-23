package com.github.ashim.provider.service;

import java.util.List;

import com.github.ashim.persistence.entity.User;

public interface UserService {

	public List<User> getAllUsers();

	public User getUserById(Integer id);

}
