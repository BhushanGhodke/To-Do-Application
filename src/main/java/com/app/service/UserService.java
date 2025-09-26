package com.app.service;

import com.app.binding.Login;
import com.app.entity.User;

public interface UserService {

	public User SaveUser(User user);

	public User LoginUser(Login login);
}
