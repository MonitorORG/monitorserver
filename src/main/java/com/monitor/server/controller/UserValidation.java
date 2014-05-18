package com.monitor.server.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.luckyryan.sample.dao.model.UserEntity;

@Component("userValidator")
public class UserValidation {
	public boolean supports(Class<?> klass) {
		return UserEntity.class.isAssignableFrom(klass);
	}
	
	public void validate(Object target, Errors errors) {
		UserEntity registration = (UserEntity) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
				"NotEmpty.registration.username",
				"用户名不能为空.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"NotEmpty.registration.password",
				"密码不能为空.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"NotEmpty.registration.password",
				"邮箱不能为空.");
		
		String username = registration.getUsername();
		if ((username.length()) > 50) {
			errors.rejectValue("username",
				"lengthOfUser.registration.username",
				"用户名不能多与50个字符.");
		}
		
		if (!(registration.getPassword()).equals(registration.getConfirmPassword())) {
			errors.rejectValue("password",
				"matchingPassword.registration.password",
				"密码和确认密码不匹配.");
		}
	}
}
