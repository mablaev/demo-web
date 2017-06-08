package com.mycompany.service;

import com.mycompany.domain.User;

public interface UserService {
	void save(User user);

	User findUserByEmail(String email);
}
