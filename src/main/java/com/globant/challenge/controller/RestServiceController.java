package com.globant.challenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.ServiceException;
import com.globant.challenge.rest.UserV1;
import com.globant.challenge.service.FileService;
import com.globant.challenge.service.UserService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;



@RestController
@RequestMapping("/rest")
public class RestServiceController {

	private static Logger log = LoggerFactory.getLogger(RestServiceController.class);

	@Autowired
	UserService usersService;
	
	@Autowired
	FileService fileService;

	@RequestMapping(value = "v2/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsersByProfession(@RequestParam("profession") String profession) {
		List<User> users = new ArrayList<User>();
		try {
			users = usersService.getUsersByProfession(profession);
		} catch (ServiceException e) {
			log.error("SYSTEM ERROR occurred", e);
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);		
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	/**
	 * 
	 * @param profession
	 * @return a list of users from version 1 which has only username and profession
	 */
	@RequestMapping(value = "v1/users", method = RequestMethod.GET)
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
		return new ResponseEntity<List<UserV1>>(usersV1,HttpStatus.OK);
	}
	
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
	public Map<String, Object> getDBProperties() {
		return usersService.checkDBStatus();

	}

}
