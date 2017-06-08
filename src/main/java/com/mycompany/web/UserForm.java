package com.mycompany.web;

import com.mycompany.domain.Role;
import com.mycompany.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

	private String email;
	private String password;
	private String passwordConfirm;

	public User toUser() {
		User u = new User(email, password, Role.GUEST);
		return u;
	}
}
