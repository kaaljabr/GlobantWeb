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
			// persist the user
			dao.createUser(user);
		} catch (DaoException e) {
			log.error("An error occured when calling createUser", e);
			throw new ServiceException("createUser DAO exception occured", e);
		}
	}

	@Override
	public List<User> findAllUsers() throws ServiceException {
		List<User> users = new ArrayList<User>();
		try {
			// find all users by named query
			users = dao.findUsers(null, Constants.USERS_FINDALL);
		} catch (DaoException e) {
			log.error("An error occured when calling findAllUsers", e);
			throw new ServiceException("getAllUsers DAO exception occured", e);
		}
		return users;
	}

	@Override
	public List<User> findAllUsers(int page, int limit) throws ServiceException {
		List<User> users = new ArrayList<User>();
		try {
			// page starts from 1 but offset from 0
			int offset = (page - 1) * limit;
			// get paginated users by named query
			users = dao.findUsers(null, Constants.USERS_FINDALL, offset, limit);
		} catch (DaoException e) {
			log.error("An error occured when calling findAllUsers", e);
			throw new ServiceException("getAllUsers DAO exception occured", e);
		}
		return users;
	}

	@Override
	public boolean isUsernameTaken(String username) throws ServiceException {
		try {
			// initialize the parameters map
			Map<String, Object> parameters = new HashMap<String, Object>();
			// add username as a filter parameter
			parameters.put("username", username.toLowerCase());
			// call the DAO to find the user
			List<User> users = dao.findUsers(parameters, "Users.findByUsername");
			// check if the returned users list is not empty
			if (!users.isEmpty()) {
				log.debug("username: {} is taken", username);
				// username is taken return true
				return true;
			}
		} catch (DaoException e) {
			log.error("An error occured when calling isUsernameTaken", e);
			throw new ServiceException("isUsernameAvailable DAO exception occured", e);
		}
		// return false indicating the username is not taken
		return false;
	}

	@Override
	public boolean checkLogin(String username, String password) throws ServiceException {
		try {
			// initialize the parameters map
			Map<String, Object> parameters = new HashMap<String, Object>();
			// fill the parameters map with username parameters and password
			parameters.put("username", username.toLowerCase());
			parameters.put("password", password);
			// find users based on the named query and parameters
			List<User> users = dao.findUsers(parameters, Constants.USERS_FIND_BY_USERNAME_AND_PASSWORD);
			// check if the list of users is not empty
			if (!users.isEmpty()) {
				log.debug("username: {} log in success", username);
				// that means the login credentials are correct and log in succeeds
				return true;
			}

		} catch (DaoException e) {
			log.error("An error occured when calling checkLogin", e);
			throw new ServiceException("checkLogin DAO exception occured", e);
		}
		// login fails no users found for the provided credentials
		return false;
	}

	@Override
	public List<User> getUsersByProfession(String profession) throws ServiceException {
		List<User> users = new ArrayList<User>();
		// initialize the paramters map
		Map<String, Object> parameters = new HashMap<String, Object>();
		// add profession as a filter
		parameters.put("profession", profession.toLowerCase());
		try {
			// find users filtered by the profession paramater and ordered by the state
			users = dao.findUsers(parameters, Constants.USERS_FIND_USERS_BY_PROFESSION);
		} catch (DaoException e) {
			log.error("An error occured when calling getUsersByProfession", e);
			throw new ServiceException("getUsersByProfession DAO exception occured", e);
		}
		return users;
	}

	@Override
	public Map<String, Object> checkDBStatus() throws ServiceException {
		try {
			// check the status of the database here
			return dao.checkDBStatus();
		} catch (DaoException e) {
			throw new ServiceException("Dumping users failed", e);
		}
	}

	@Override
	public void dumpUsers(String nativeQuery) throws ServiceException {
		try {
			// execute a native query to dump users
			dao.executeNativeQuery(nativeQuery);
		} catch (DaoException e) {
			throw new ServiceException("Dumping users failed", e);
		}

	}

}
