package com.globant.challenge.service;

import java.util.List;

import com.globant.challenge.bean.User;

public interface UserService {
	
	public void createUser(User user);
	
	public List<User> getAllUsers();

}
