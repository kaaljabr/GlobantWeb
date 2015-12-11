package com.globant.challenge.service;

import java.util.List;
import java.util.Map;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.ServiceException;

public interface UserService {

	/**
	 * Check if the username is taken
	 *
	 * @param username
	 *            Username string to check against
	 * @return A boolean indicating if taken or not
	 * @throws ServiceException
	 *             Any error thrown by DAO layer is wrapped and thrown as ServiceException
	 */
	public boolean isUsernameTaken(String username) throws ServiceException;

	/**
	 * Check login by providing username and password
	 *
	 * @param username
	 *            String containing the username
	 * @param password
	 *            String containing the password
	 * @return a boolean indicating if login succeeds of fails
	 * @throws ServiceException
	 *             Any error thrown by DAO layer is wrapped and thrown as ServiceException
	 */
	public boolean checkLogin(String username, String password) throws ServiceException;

	/**
	 * Return users filtered by their profession and ordered by their state
	 *
	 * @param profession
	 *            String containing the profession to filter by
	 * @return A list of users filtered by profession and ordered by their state
	 * @throws ServiceException
	 *             Any error thrown by DAO layer is wrapped and thrown as ServiceException
	 */
	public List<User> getUsersByProfession(String profession) throws ServiceException;

	/**
	 * Create a user and persist it in the DataBase
	 *
	 * @param user
	 *            A user to be persisted
	 * @throws ServiceException
	 *             Any error thrown by DAO layer is wrapped and thrown as ServiceException
	 */
	public void createUser(User user) throws ServiceException;

	/**
	 * Return all the users from the DataBase
	 *
	 * @return A list of users
	 * @throws ServiceException
	 *             Any error thrown by DAO layer is wrapped and thrown as ServiceException
	 */
	public List<User> findAllUsers() throws ServiceException;

	/**
	 * Return paginated list of users
	 *
	 * @param page
	 *            Starting index for the users to look for
	 * @param limit
	 *            Max size of users to return
	 * @return List of paginated users
	 * @throws ServiceException
	 *             Any error thrown by DAO layer is wrapped and thrown as ServiceException
	 */
	public List<User> findAllUsers(int page, int limit) throws ServiceException;

	/**
	 * This method is used to get the status and properties of the DB
	 *
	 * @return
	 * @throws ServiceException
	 *             Any error thrown by DAO layer is wrapped and thrown as ServiceException
	 */
	public Map<String, Object> checkDBStatus() throws ServiceException;

	/**
	 * Dump users into the database
	 *
	 * @param nativeQuery
	 *            Query to execute in Native SQL
	 * @throws ServiceException
	 *             Any error thrown by DAO layer is wrapped and thrown as ServiceException
	 */
	public void dumpUsers(String nativeQuery) throws ServiceException;

}
