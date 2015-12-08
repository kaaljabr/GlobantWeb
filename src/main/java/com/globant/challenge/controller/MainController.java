package com.globant.challenge.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.PasswordManagerException;
import com.globant.challenge.service.UserService;
import com.globant.challenge.utils.PasswordManager;
import com.globant.challenge.validator.LoginValidator;
import com.globant.challenge.validator.RegisterUserValidator;

@Controller
public class MainController {

	private static Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	UserService service;

	@Autowired
	LoginValidator loginValidator;

	@Autowired
	RegisterUserValidator registerUserValidator;

	@Autowired
	protected MessageSource resource;
	
	@Autowired
	User user;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView("welcomePage");
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("loginPage");
		return mv;
	}

	// handles person form submit
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, BindingResult result, HttpSession session) {
		loginValidator.validate(user, result);
		if (result.hasErrors()) {
			log.warn("validation fails");
			return "loginPage";
		} else {
			this.user = user;
			log.warn("validation passed");
			return "redirect:welcome";
		}
	}

	@RequestMapping(value = "/createTestUser", method = RequestMethod.GET)
	@ResponseBody
	public String createTestUser() {
		User user = new User();
		user.setUsername("test");
		try {
			user.setPassword(PasswordManager.getInstance().encrypt("password"));
		} catch (PasswordManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		service.createUser(user);

		return "success";
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(User user, BindingResult result) {
		registerUserValidator.validate(user, result);
		if (result.hasErrors()) {
			log.warn("validation fails");
			StringBuilder sb = new StringBuilder("");
			for (ObjectError objectError : result.getAllErrors()) {
				sb.append(resource.getMessage(objectError.getCode(), null, Locale.US));
				sb.append("<br>");
			}
			return sb.toString();
		} else {
			log.warn("validation passed");
			return "success";
		}
		/*
		 * String password = user.getPassword(); try {
		 * user.setPassword(PasswordManager.getInstance().encrypt(password)); }
		 * catch (PasswordManagerException e) { log.error(
		 * "Error occured when trying to encrypt the password", e); }
		 * service.createUser(user); return "User Created Successfully!!!";
		 */
	}

}
