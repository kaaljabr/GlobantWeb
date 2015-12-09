package com.globant.challenge.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.DaoException;

public interface UserDAO {

	public List<User> getUsersByParams(Map<String, Object> parameters, String queryString) throws NoResultException, DaoException;

	public Map<String, Object> checkDBStatus();

	public void createUser(User user);

	public List<User> getAllUsers();

}
