package com.globant.challenge.dao;

import java.util.List;
import java.util.Map;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.DaoException;

public interface UserDAO {

	/**
	 * This method is used to create and pesist a user
	 *
	 * @param user
	 *            A user bean to persist in the database
	 * @throws DaoException
	 *             Any database exception is wrapped into this custom exception
	 */
	public void createUser(User user) throws DaoException;

	/**
	 * This method is used to find users
	 *
	 * @param parameters
	 *            A map of parameters for filtering purpose
	 * @param queryString
	 *            This has to be a named query
	 * @return A list of users
	 * @throws DaoException
	 *             Database exception is wrapped into this custom exception
	 */
	public List<User> findUsers(Map<String, Object> parameters, String queryString) throws DaoException;

	/**
	 * This method is used to find and return paginated users
	 *
	 * @param parameters
	 *            A map of parameters for filtering purpose
	 * @param queryString
	 *            This has to be a named query
	 * @param offset
	 *            Starting index for the records to look for
	 * @param limit
	 *            Max size of records to return
	 * @return A list of users
	 * @throws DaoException
	 *             Database exception is wrapped into this custom exception
	 */
	public List<User> findUsers(Map<String, Object> parameters, String queryString, int offset, int limit) throws DaoException;

	/**
	 * This method is used to get the status and properties of the DB
	 *
	 * @return A map of DB properties
	 * @throws DaoException
	 *             Database exception is wrapped into this custom exception
	 */
	public Map<String, Object> checkDBStatus() throws DaoException;

	/**
	 * Execute a native query
	 *
	 * @param nativeQuery
	 *            A native query
	 * @throws DaoException
	 *             Database exception is wrapped into this custom exception
	 */
	public void executeNativeQuery(String nativeQuery) throws DaoException;

}
