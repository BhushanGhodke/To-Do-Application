package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
	
}
