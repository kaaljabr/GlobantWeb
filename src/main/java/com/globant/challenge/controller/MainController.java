package com.globant.challenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.ServiceException;
import com.globant.challenge.exception.UtilsException;
import com.globant.challenge.service.UserService;
import com.globant.challenge.utils.Constants;
import com.globant.challenge.utils.PasswordManager;
import com.globant.challenge.validator.LoginValidator;

@Controller
public class MainController {

	private static Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	UserService service;

	@Autowired
	LoginValidator loginValidator;

	@Autowired
	protected MessageSource resource;

	@Autowired
	User user;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView getLandingView() {
		if (user.getUsername() == null) {
			return new ModelAndView("redirect:login");
		}
		ModelAndView mv = new ModelAndView("landingView");
		mv.addObject("username", user.getUsername());
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("loginPage");
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(User user, BindingResult result, HttpSession session) {
		loginValidator.validate(user, result);
		if (result.hasErrors()) {
			log.warn("validation fails");
			return new ModelAndView("loginPage");
		} else {
			try {
				user.setPassword(PasswordManager.getInstance().encrypt(user.getPassword()));
				boolean success = service.checkLogin(user.getUsername(), user.getPassword());
				// check if login fails return an error message
				if (!success) {
					log.warn("Log in fails");
					ModelAndView mv = new ModelAndView("loginPage");
					mv.addObject("errorMsg", resource.getMessage(Constants.LOGIN_FAILED, null, Locale.US));
					return mv;
				}
			} catch (ServiceException | UtilsException e) {
				log.error("SYSTEM ERROR occurred", e);
				ModelAndView mv = new ModelAndView("loginPage");
				mv.addObject("errorMsg", resource.getMessage(Constants.SERVICE_ERROR_OCCURRED, null, Locale.US));
				return mv;
			}
			// save successful logged in user in auto wired user session scoped
			this.user = user;
			log.warn("validation passed");
			return new ModelAndView("redirect:main");
		}
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			log.warn("validation fails");
			StringBuilder sb = new StringBuilder("");
			for (ObjectError objectError : result.getAllErrors()) {
				sb.append(resource.getMessage(objectError.getDefaultMessage(), null, Locale.US));
				sb.append("<br>");
			}
			return sb.toString();
		} else {

			try {
				boolean usernameTaken = service.isUsernameTaken(user.getUsername());
				if (usernameTaken) {
					return resource.getMessage(Constants.USERNAME_TAKEN, null, Locale.US);
				}
				// encrypting the password before saving the user
				user.setPassword(PasswordManager.getInstance().encrypt(user.getPassword()));
				user.setUsername(user.getUsername().toLowerCase());
				// save user to the DB
				service.createUser(user);
			} catch (ServiceException | UtilsException e) {
				log.error("SYSTEM ERROR occurred", e);
				return resource.getMessage(Constants.SERVICE_ERROR_OCCURRED, null, Locale.US);
			}
			this.user = user;
			return "success";
		}
	}

	@RequestMapping(value = "/usersByProfession", method = RequestMethod.GET)
	public ModelAndView getUsersByProfession(@RequestParam("profession") String profession) {
		if (user.getUsername() == null) {
			return new ModelAndView("redirect:login");
		}
		List<User> users = new ArrayList<User>();
		try {
			users = service.getUsersByProfession(profession);
		} catch (ServiceException e) {
			log.error("SYSTEM ERROR occurred", e);
		}
		ModelAndView mv = new ModelAndView("usersLookupView");
		mv.addObject("users", users);
		return mv;
	}

	@RequestMapping(value = "/createTestUser", method = RequestMethod.GET)
	@ResponseBody
	public String createTestUser() {
		User user = new User();
		user.setUsername("test");
		try {
			user.setPassword(PasswordManager.getInstance().encrypt("password"));
		} catch (UtilsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		service.createUser(user);

		return "success";
	}

}
