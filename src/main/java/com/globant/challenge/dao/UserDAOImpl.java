package com.globant.challenge.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.globant.challenge.bean.User;
@Repository
@Transactional
public class UserDAOImpl implements UserDAO{
	
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
    private EntityManager manager;
	
	@Override
	public void createUser(User user) {
		manager.persist(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
