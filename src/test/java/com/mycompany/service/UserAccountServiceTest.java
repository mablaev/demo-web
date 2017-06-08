package com.mycompany.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.BaseTest;
import com.mycompany.domain.Role;
import com.mycompany.domain.User;
import com.mycompany.domain.UserAccount;

public class UserAccountServiceTest extends BaseTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserAccountService userAccountService;

	private User testUser;

	@Before
	public void setUp() {
		testUser = new User("mablaev@gmail.com", "testPwd", Role.ADMIN);
		userService.save(testUser);

		UserAccount ua = new UserAccount(testUser, BigDecimal.valueOf(123));
		userAccountService.save(ua);
	}

	@Test
	public void findUserAccountByUserId() {
		UserAccount foundUserAcc = userAccountService.findUserAccountByUserId(testUser.getId());
		assertThat(foundUserAcc, notNullValue());
	}

	@Test
	public void paginateOverAccounts() {
		List<UserAccount> allUserAccounts = userAccountService.findAllUserAccounts(0, 10, 5);
		assertThat(allUserAccounts.size(), is(10));
	}

}
