package com.globant.challenge.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.globant.challenge.bean.User;

@Component
public class RegisterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", "username.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.required");
		ValidationUtils.rejectIfEmpty(errors, "state", "state.required");
		ValidationUtils.rejectIfEmpty(errors, "profession", "profession.required");

		// do "complex" validation here

	}

}
