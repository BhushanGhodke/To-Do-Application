package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.binding.Login;
import com.app.entity.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> createNewUser(@RequestBody User user) {
		User saveUser = userService.SaveUser(user);

		return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody Login login) {
		User loginUser = userService.LoginUser(login);

		return new ResponseEntity<User>(loginUser, HttpStatus.OK);
	}
}
