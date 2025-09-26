package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.binding.Login;
import com.app.entity.User;
import com.app.exception.UserNotFoundException;
import com.app.repo.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User SaveUser(User user) {

		Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
		
		if(userFromDB.isEmpty()) {
			
			return  userRepository.save(user);
		}
		else {
			throw new RuntimeException("User Alredy Exists");
		}

	}

	
	@Override
	public User LoginUser(Login login) {
	
		Optional<User> user = userRepository.findByUsername(login.getUsername());
		
		if(user.isPresent() && user.get().getPassword().equals(login.getPassword())) {

			return user.get();
			
		}
		throw new UserNotFoundException("User Not Found");
	}
}
