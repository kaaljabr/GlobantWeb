package com.globant.challenge.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challenge.bean.User;
import com.globant.challenge.dao.UserDAO;
import com.globant.challenge.exception.DaoException;
import com.globant.challenge.exception.ServiceException;
import com.globant.challenge.utils.Constants;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

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
	public boolean isUsernameTaken(String username) throws ServiceException {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("username", username.toLowerCase());
			List<User> users = dao.getUsersByParams(parameters, "Users.findByUsername");
			if (!users.isEmpty()) {
				log.debug("username: {} is taken", username);
				return true;
			}
		} catch (DaoException e) {
			log.error("DAO ERROR occurred !!!!", e);
			throw new ServiceException("isUsernameAvailable DAO exception occured", e);
		}
		return false;
	}

	@Override
	public boolean checkLogin(String username, String password) throws ServiceException {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("username", username.toLowerCase());
			parameters.put("password", password);
			List<User> users = dao.getUsersByParams(parameters, Constants.USERS_FIND_BY_USERNAME_AND_PASSWORD);
			if (!users.isEmpty()) {
				log.debug("username: {} log in success", username);
				return true;
			}

		} catch (DaoException e) {
			log.error("DAO ERROR occurred !!!!", e);
			throw new ServiceException("isUsernameAvailable DAO exception occured", e);
		}
		return false;
	}

	@Override
	public List<User> getUsersByProfession(String profession) throws ServiceException {
		List<User> users = new ArrayList<User>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("profession", profession.toLowerCase());
		try {
			users = dao.getUsersByParams(parameters, Constants.USERS_FIND_USERS_BY_PROFESSION);
		} catch (DaoException e) {
			log.error("DAO ERROR occurred !!!!", e);
			throw new ServiceException("isUsernameAvailable DAO exception occured", e);
		}
		return users;
	}

	@Override
	public Map<String, Object> checkDBStatus() {
		return dao.checkDBStatus();
	}

}
