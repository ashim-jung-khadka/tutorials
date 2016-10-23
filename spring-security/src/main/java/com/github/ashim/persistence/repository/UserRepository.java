package com.github.ashim.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ashim.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

}
