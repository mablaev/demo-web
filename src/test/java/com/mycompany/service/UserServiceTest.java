package com.mycompany.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mycompany.BaseTest;
import com.mycompany.domain.Role;
import com.mycompany.domain.User;

public class UserServiceTest extends BaseTest {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Test
	public void saveUser() {
		User user = new User("mablaev@gmail.com", "testPwd", Role.ADMIN);
		userService.save(user);

		User foundUser = userService.findUserByEmail("mablaev@gmail.com");

		assertThat(foundUser, notNullValue());
		assertThat(foundUser.getId(), notNullValue());
		assertThat(foundUser.getRegistrationDate(), notNullValue());
		assertThat(foundUser.getRole(), is(Role.USER));
		assertThat(pwdEncoder.matches("testPwd", foundUser.getPassword()), is(true));
	}

}
