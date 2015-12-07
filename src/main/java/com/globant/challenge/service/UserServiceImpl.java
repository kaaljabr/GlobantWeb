package com.globant.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challenge.bean.User;
import com.globant.challenge.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService{

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

}
