package com.globant.challenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.ServiceException;
import com.globant.challenge.rest.UserV1;
import com.globant.challenge.service.FileService;
import com.globant.challenge.service.UserService;

@RestController
@RequestMapping("/rest")
public class RestServiceController {

	private static Logger log = LoggerFactory.getLogger(RestServiceController.class);

	@Autowired
	UserService usersService;

	@Autowired
	FileService fileService;

	/**
	 *
	 * @param profession
	 *            a query param with profession to filter users
	 * @return a list of users of version 2 which has id, username, profession, and state properties
	 */
	@RequestMapping(value = "v2/filter/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsersByProfession(@RequestParam("profession") String profession) {
		List<User> users = new ArrayList<User>();
		try {
			users = usersService.getUsersByProfession(profession);
		} catch (ServiceException e) {
			log.error("SYSTEM ERROR occurred", e);
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 *
	 * @param profession
	 *            a query param with profession to filter users
	 * @return a list of users from version 1 (old implementation) which has only username and
	 *         profession properties
	 */
	@RequestMapping(value = "v1/filter/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserV1>> getUsersByProfessionV1(@RequestParam("profession") String profession) {
		Mapper mapper = new DozerBeanMapper();
		List<UserV1> usersV1 = new ArrayList<UserV1>();
		List<User> users = new ArrayList<User>();
		try {
			users = usersService.getUsersByProfession(profession);
			for (User user : users) {
				UserV1 userV1 = mapper.map(user, UserV1.class);
				usersV1.add(userV1);
			}
		} catch (ServiceException e) {
			log.error("SYSTEM ERROR occurred", e);
			return new ResponseEntity<List<UserV1>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<UserV1>>(usersV1, HttpStatus.OK);
	}

	/**
	 *
	 * @param page
	 *            this is the page number, it starts from 1
	 * @param limit
	 *            the number of results returned per page.
	 * @return returns a list of users with version 2 details in JSON format and a status 200, in
	 *         case of an service error a 400 error state is returned
	 */
	@RequestMapping(value = "v2/users/{page}/{limit}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getPaginatedUsers(@PathVariable int page, @PathVariable int limit) {
		List<User> users = new ArrayList<User>();
		try {
			users = usersService.findAllUsers(page, limit);
		} catch (ServiceException e) {
			log.error("SYSTEM ERROR occurred", e);
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 *
	 * @param page
	 *            this is the page number, it starts from 1
	 * @param limit
	 *            the number of results returned per page.
	 * @return returns a list of users with version 1 (old implementation) details in JSON format
	 *         and a status 200, if a ServiceException is caught a 400 error state is returned
	 */
	@RequestMapping(value = "v1/users/{page}/{limit}", method = RequestMethod.GET)
	public ResponseEntity<List<UserV1>> getPaginatedUsersV1(@PathVariable int page, @PathVariable int limit) {
		Mapper mapper = new DozerBeanMapper();
		List<UserV1> usersV1 = new ArrayList<UserV1>();
		List<User> users = new ArrayList<User>();
		try {
			users = usersService.findAllUsers(page, limit);
			for (User user : users) {
				UserV1 userV1 = mapper.map(user, UserV1.class);
				usersV1.add(userV1);
			}
		} catch (ServiceException e) {
			log.error("SYSTEM ERROR occurred", e);
			return new ResponseEntity<List<UserV1>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<UserV1>>(usersV1, HttpStatus.OK);
	}

	/**
	 *
	 * @param directory
	 *            path to the directory to find all files in
	 * @return JSON list of file names with a status 200, if a ServiceException is caught a 400
	 *         error state is returned
	 */
	@RequestMapping(value = "/files", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getFilesInDirectory(@RequestParam("directory") String directory) {
		List<String> fileNames = new ArrayList<String>();
		try {
			fileNames = fileService.readFiles(directory);
		} catch (ServiceException e) {
			log.error("SYSTEM ERROR occurred", e);
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<String>>(fileNames, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkDBStatus", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getDBProperties() {
		try {
			return new ResponseEntity<Map<String, Object>>(usersService.checkDBStatus(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}

	}

}
