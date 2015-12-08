package com.globant.challenge.service;

import java.util.List;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.ServiceException;

public interface UserService {

	public boolean isUsernameAvailable(String username) throws ServiceException;

	public boolean checkLogin(String username, String password) throws ServiceException;

	public void createUser(User user);

	public List<User> getAllUsers();

}
