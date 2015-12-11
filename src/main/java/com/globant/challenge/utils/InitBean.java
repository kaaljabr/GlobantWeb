package com.globant.challenge.utils;

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
				userService.dumpUsers(PropertiesManager.getInstance().getProperty(Constants.USERS_DB_DUMP));
			}
		} catch (ServiceException e) {
			log.error("Dumping users failed", e);
		}

	}

}
