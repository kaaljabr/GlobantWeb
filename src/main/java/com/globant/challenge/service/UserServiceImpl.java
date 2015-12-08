package com.globant.challenge.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challenge.bean.User;
import com.globant.challenge.dao.UserDAO;
import com.globant.challenge.exception.DaoException;
import com.globant.challenge.exception.ServiceException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUser(User user) {
		dao.createUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameAvailable(String username) throws ServiceException {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("username", username);
			dao.getUserByParams(parameters, "Users.findByUsername");
		} catch (DaoException e) {
			throw new ServiceException("isUsernameAvailable DAO exception occured", e);
		} catch (NoResultException e) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkLogin(String username, String password) throws ServiceException {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("username", username);
			parameters.put("password", password);
			dao.getUserByParams(parameters, "Users.findByUsernameAndPassword");
		} catch (DaoException e) {
			throw new ServiceException("isUsernameAvailable DAO exception occured", e);
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}

}
