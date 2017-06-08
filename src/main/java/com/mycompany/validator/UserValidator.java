package com.mycompany.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mycompany.service.UserService;
import com.mycompany.web.UserForm;

@Component
public class UserValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserForm.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UserForm user = (UserForm) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

		if (userService.findUserByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Duplicate");
		}

		if (!emailPattern.matcher(user.getEmail()).matches()) {
			errors.rejectValue("email", "Size.userForm.email");
		}
	}
}