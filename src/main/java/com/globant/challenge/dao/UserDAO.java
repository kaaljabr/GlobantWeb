package com.globant.challenge.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.DaoException;

public interface UserDAO {

	public List<User> findUsers(Map<String, Object> parameters, String queryString) throws NoResultException, DaoException;

	public List<User> findUsers(Map<String, Object> parameters, String queryString, int offset, int limit) throws NoResultException, DaoException;

	public Map<String, Object> checkDBStatus();

	public void dumpUsers() throws DaoException;

	public void createUser(User user) throws DaoException;

}
