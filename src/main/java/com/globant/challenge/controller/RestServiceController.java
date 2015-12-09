package com.globant.challenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.ServiceException;
import com.globant.challenge.service.UserService;

@RestController
@RequestMapping("/rest")
public class RestServiceController {

	private static Logger log = LoggerFactory.getLogger(RestServiceController.class);

	@Autowired
	UserService service;

	@RequestMapping(value = "/usersByProfession", method = RequestMethod.GET)
	public List<User> getUsersByProfession(@RequestParam("profession") String profession) {
		List<User> users = new ArrayList<User>();
		try {
			users = service.getUsersByProfession(profession);
		} catch (ServiceException e) {
			log.error("SYSTEM ERROR occurred", e);
		}
		return users;
	}

	@RequestMapping(value = "/checkDBStatus", method = RequestMethod.GET)
	public Map<String, Object> getDBProperties() {
		return service.checkDBStatus();

	}

}
