package com.mycompany.service;

import java.util.List;

import com.mycompany.domain.UserAccount;

public interface UserAccountService {
	UserAccount findUserAccountByUserId(Long userId);

	UserAccount findUserAccountByUserEmail(String email);

	List<UserAccount> findAllUserAccounts(int from, int legth, int orderColumn);

	void save(UserAccount ua);

	long count();

}
