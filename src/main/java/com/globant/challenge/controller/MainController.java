package com.globant.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.globant.challenge.bean.User;
import com.globant.challenge.exception.PasswordManagerException;
import com.globant.challenge.service.UserService;
import com.globant.challenge.utils.PasswordManager;

@Controller
public class MainController {

	String message = "Welcome to Spring MVC!";
	
	@Autowired
	UserService service;
	 
	@RequestMapping(value="/login" , method=RequestMethod.GET)
	public ModelAndView login() { 
		ModelAndView mv = new ModelAndView("loginPage");		
		return mv;
	}
	
	// handles person form submit
    @RequestMapping(value="/submitLogin", method=RequestMethod.POST)
    @ResponseBody
    public String submitLogin(User person) {
        //personService.save(person);
        return "Login Successful!!";
    }
	
    @RequestMapping(value="/createTestUser", method=RequestMethod.GET)
    @ResponseBody
    public String createUser(User person) {
        User user = new User();
        user.setUsername("test");
        try {
			user.setPassword(PasswordManager.getInstance().encrypt("password"));
		} catch (PasswordManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        service.createUser(user);
        
        try {
			return "User: "+user.getUsername()+" created successfully!! password after encryption is: "+user.getPassword()+" password after decryption is: "+PasswordManager.getInstance().decrypt(user.getPassword());
		} catch (PasswordManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ":(";
    }
	
	
	
}
