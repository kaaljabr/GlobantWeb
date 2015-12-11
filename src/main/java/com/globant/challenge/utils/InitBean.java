package com.globant.challenge.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.ServiceException;
import com.globant.challenge.service.UserService;

@Component
public class InitBean implements InitializingBean {

	private static Logger log = LoggerFactory.getLogger(InitBean.class);

	@Autowired
	UserService userService;

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			log.debug("RUNNING InitBean init method now !!!!!!!!!");

			List<User> users = userService.findAllUsers();
			// check if USERS table has content
			if (users.isEmpty()) {
				// dump DB USERS table with data from config.properties insert query
				// userService.dumpUsers(PropertiesManager.getInstance().getProperty(Constants.USERS_DB_DUMP));
				for (User user : buildUsers()) {
					userService.createUser(user);
				}
			}
		} catch (ServiceException e) {
			log.error("Dumping users failed", e);
		}

	}

	private List<User> buildUsers() {
		List<User> users = new ArrayList<User>();

		users.add(new User("kareem", "PM+Xzo1CnT8=", "Software Engineer", "Kansas"));
		users.add(new User("john", "PM+Xzo1CnT8=", "Electrical Engineer", "Washington"));
		users.add(new User("wiliam", "PM+Xzo1CnT8=", "Electrical Engineer", "Virginia"));
		users.add(new User("ronaldo", "PM+Xzo1CnT8=", "Electrical Engineer", "Texas"));
		users.add(new User("marcelo", "PM+Xzo1CnT8=", "System Admin", "Texas"));
		users.add(new User("ramos", "PM+Xzo1CnT8=", "System Admin", "Virginia"));
		users.add(new User("suarez", "PM+Xzo1CnT8=", "Accountant", "Virginia"));
		users.add(new User("james", "PM+Xzo1CnT8=", "Accountant", "Washington"));
		users.add(new User("howard", "PM+Xzo1CnT8=", "Designer", "Texas"));
		users.add(new User("gerrard", "PM+Xzo1CnT8=", "Software Engineer", "Texas"));
		users.add(new User("benteke", "PM+Xzo1CnT8=", "Designer", "Kansas"));
		users.add(new User("alfredo", "PM+Xzo1CnT8=", "Designer", "Washington"));
		users.add(new User("coutinho", "PM+Xzo1CnT8=", "Software Engineer", "Ohio"));
		users.add(new User("albert", "PM+Xzo1CnT8=", "Accountant", "Ohio"));
		users.add(new User("alvaro", "PM+Xzo1CnT8=", "Electrical Engineer", "Virginia"));

		return users;
	}

}
