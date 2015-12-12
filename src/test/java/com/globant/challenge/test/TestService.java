package com.globant.challenge.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.ServiceException;
import com.globant.challenge.exception.UtilsException;
import com.globant.challenge.service.FileService;
import com.globant.challenge.service.UserService;
import com.globant.challenge.utils.Constants;
import com.globant.challenge.utils.PasswordManager;
import com.globant.challenge.utils.PropertiesManager;

@ContextConfiguration(locations = { "classpath:dispatcher-servlet-test.xml", "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestService {

	@Autowired
	UserService userService;

	@Autowired
	FileService fileService;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUserCreation() {
		try {
			//persist a new user
			User user = new User("Fred", PasswordManager.getInstance().encrypt("123456"), "Quality Assurance", "Oklahoma");
			userService.createUser(user);
			//make sure user name is persisted
			boolean usernameTaken = userService.isUsernameTaken(user.getUsername());
			Assert.assertEquals(usernameTaken, true);
		} catch (ServiceException | UtilsException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFetchUsers() {
		// make sure the dump is working fine and there are 11 records available
		// in the DB
		try {
			List<User> users = userService.findAllUsers();
			Assert.assertEquals(users.size(), 15);
		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUserLogin() {
		// make sure login passes when providing correct login credentials
		try {
			boolean success = userService.checkLogin("kareem", PasswordManager.getInstance().encrypt("123456"));
			Assert.assertEquals(success, true);
		} catch (ServiceException | UtilsException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUserLoginFailure() {
		// test login failure
		try {
			boolean success = userService.checkLogin("kareem", PasswordManager.getInstance().encrypt("654321"));
			Assert.assertFalse(success);
		} catch (ServiceException | UtilsException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUsernameIsTaken() {
		// make sure username is taken
		try {
			boolean taken = userService.isUsernameTaken("kareem");
			Assert.assertEquals(taken, true);
		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUsernameIsNotTaken() {
		// make sure username is not taken
		try {
			boolean taken = userService.isUsernameTaken("roberto");
			Assert.assertFalse(taken);
		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUsersByProfession() {
		// make sure when searching for users who have profession accountant we
		// have 3
		try {
			List<User> users = userService.getUsersByProfession("accountant");
			Assert.assertEquals(users.size(), 3);
		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUsersPagination() {
		// make sure when pagination with limit 3 retruns 3 records only
		try {
			List<User> users = userService.findAllUsers(1, 3);
			Assert.assertEquals(users.size(), 3);
		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUsersPaginationRecord() {
		// make sure when returning paginated users the first record in the
		// second page is walter
		try {
			List<User> users = userService.findAllUsers(2, 3);
			Assert.assertEquals(users.get(0).getUsername(), "ronaldo");
		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(expected = IOException.class)
	public void testReadFilesIOException() throws IOException {
		// make sure there is one file in the specified directory
		try {
			List<String> filesNames = fileService.readFiles(PropertiesManager.getInstance().getProperty(Constants.WRONG_DIRECTORY_PATH));
			Assert.assertEquals(filesNames.size(), 1);
		} catch (ServiceException e) {
			if (e.getCause() instanceof IOException) {
				throw new IOException();
			}
		}
	}

	@Test
	public void testReadFiles() {
		// make sure there is one file in the specified directory
		try {
			List<String> filesNames = fileService.readFiles(PropertiesManager.getInstance().getProperty(Constants.CORRECT_DIRECTORY_PATH));
			Assert.assertEquals(filesNames.size(), 1);
		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testReadFileName() {
		// make sure there is a file named test.txt
		try {
			List<String> filesNames = fileService.readFiles(PropertiesManager.getInstance().getProperty(Constants.CORRECT_DIRECTORY_PATH));
			Assert.assertEquals(filesNames.get(0), PropertiesManager.getInstance().getProperty(Constants.TEST_FILENAME));
		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
}
