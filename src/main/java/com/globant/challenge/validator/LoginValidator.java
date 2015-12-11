package com.globant.challenge.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.globant.challenge.bean.User;

@Component
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// check if username or password are empty
		ValidationUtils.rejectIfEmpty(errors, "username", "username.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.required");
	}

}
