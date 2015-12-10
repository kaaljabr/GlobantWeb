package com.globant.challenge.service;

import java.util.List;
import java.util.Map;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.ServiceException;

public interface UserService {

	public boolean isUsernameTaken(String username) throws ServiceException;

	public boolean checkLogin(String username, String password) throws ServiceException;

	public List<User> getUsersByProfession(String profession) throws ServiceException;

	public void createUser(User user) throws ServiceException;

	public List<User> findAllUsers() throws ServiceException;

	public List<User> findAllUsers(int page, int limit) throws ServiceException;

	public Map<String, Object> checkDBStatus();

	public void dumpUsers() throws ServiceException;

}
