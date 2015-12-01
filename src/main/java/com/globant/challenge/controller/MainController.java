package com.globant.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.globant.challenge.bean.Person;

@Controller
public class MainController {

	String message = "Welcome to Spring MVC!";
	 
	@RequestMapping(value="/login" , method=RequestMethod.GET)
	public ModelAndView login() { 
		ModelAndView mv = new ModelAndView("loginPage");		
		return mv;
	}
	
	// handles person form submit
    @RequestMapping(value="/submitLogin", method=RequestMethod.POST)
    @ResponseBody
    public String savePerson(Person person) {
        //personService.save(person);
        return "Login Successful!!";
    }
	
	
	
	
}
