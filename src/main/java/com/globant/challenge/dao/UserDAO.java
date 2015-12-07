package com.globant.challenge.dao;

import java.util.List;

import com.globant.challenge.bean.User;

public interface UserDAO {
	
	public void createUser(User user);
	
	public List<User> getAllUsers();

}
