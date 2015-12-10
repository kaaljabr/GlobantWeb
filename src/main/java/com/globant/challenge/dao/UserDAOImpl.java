package com.globant.challenge.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.DaoException;
import com.globant.challenge.utils.Constants;
import com.globant.challenge.utils.PropertiesManager;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void createUser(User user) throws DaoException {
		try {
			manager.persist(user);
		} catch (Exception e) {
			log.error("An error occured when calling createUser", e);
			throw new DaoException(e);
		}

	}

	@Override
	public List<User> findUsers(Map<String, Object> parameters, String queryString) throws DaoException {
		List<User> users = new ArrayList<User>();
		try {
			Query query = manager.createNamedQuery(queryString, User.class);
			// Check if we have some parameters defined
			if (parameters != null) {
				for (Entry<String, Object> entry : parameters.entrySet()) {
					// Inject the parameter to the query
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			// Execute query and return result
			users = query.getResultList();
		} catch (IllegalArgumentException | QueryTimeoutException e) {
			log.error("An error occured when calling getUserByUsername", e);
			throw new DaoException(e);
		}
		return users;
	}

	@Override
	public List<User> findUsers(Map<String, Object> parameters, String queryString, int offset, int limit) throws NoResultException, DaoException {
		List<User> users = new ArrayList<User>();
		try {
			Query query = manager.createNamedQuery(queryString, User.class);
			// Check if we have some parameters defined
			if (parameters != null) {
				for (Entry<String, Object> entry : parameters.entrySet()) {
					// Inject the parameter to the query
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			// Execute query and return result here set the offset and limit
			users = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		} catch (IllegalArgumentException | QueryTimeoutException e) {
			log.error("An error occured when calling getUserByUsername", e);
			throw new DaoException(e);
		}
		return users;
	}

	@Override
	public Map<String, Object> checkDBStatus() {
		return manager.getEntityManagerFactory().getProperties();
	}

	@Override
	public void dumpUsers() throws DaoException {
		try {
			// execute a query from config.properties.
			Query q = manager.createNativeQuery(PropertiesManager.getInstance().getProperty(Constants.USERS_DB_DUMP));
			q.executeUpdate();
		} catch (Exception e) {
			log.error("DB ERROR dumpUsers", e);
			throw new DaoException(e);
		}
	}

}
