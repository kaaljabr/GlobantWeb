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
	public void createUser(User user) throws ServiceException {
		try {
			dao.createUser(user);
		} catch (DaoException e) {
			log.error("An error occured when calling createUser", e);
			throw new ServiceException("Error creating a user", e);
		}
	}

	@Override
	public List<User> findAllUsers() throws ServiceException {
		List<User> users = new ArrayList<User>();
		try {
			users = dao.findUsers(null, Constants.USERS_FINDALL);
		} catch (DaoException e) {
			log.error("DAO ERROR occurred !!!!", e);
			throw new ServiceException("getAllUsers DAO exception occured", e);
		}
		return users;
	}

	@Override
	public List<User> findAllUsers(int page, int limit) throws ServiceException {
		List<User> users = new ArrayList<User>();
		try {
			int offset = (page - 1) * limit;
			users = dao.findUsers(null, Constants.USERS_FINDALL, offset, limit);
		} catch (DaoException e) {
			log.error("DAO ERROR occurred !!!!", e);
			throw new ServiceException("getAllUsers DAO exception occured", e);
		}
		return users;
	}

	@Override
	public boolean isUsernameTaken(String username) throws ServiceException {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("username", username.toLowerCase());
			List<User> users = dao.findUsers(parameters, "Users.findByUsername");
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
			List<User> users = dao.findUsers(parameters, Constants.USERS_FIND_BY_USERNAME_AND_PASSWORD);
			if (!users.isEmpty()) {
				log.debug("username: {} log in success", username);
				return true;
			}

		} catch (DaoException e) {
			log.error("DAO ERROR occurred !!!!", e);
			throw new ServiceException("checkLogin DAO exception occured", e);
		}
		return false;
	}

	@Override
	public List<User> getUsersByProfession(String profession) throws ServiceException {
		List<User> users = new ArrayList<User>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("profession", profession.toLowerCase());
		try {
			users = dao.findUsers(parameters, Constants.USERS_FIND_USERS_BY_PROFESSION);
		} catch (DaoException e) {
			log.error("DAO ERROR occurred !!!!", e);
			throw new ServiceException("getUsersByProfession DAO exception occured", e);
		}
		return users;
	}

	@Override
	public Map<String, Object> checkDBStatus() {
		return dao.checkDBStatus();
	}

	@Override
	public void dumpUsers() throws ServiceException {
		try {
			dao.dumpUsers();
		} catch (DaoException e) {
			throw new ServiceException("Dumping users failed", e);
		}

	}

}
