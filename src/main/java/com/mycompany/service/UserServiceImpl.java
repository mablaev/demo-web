package com.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.domain.Role;
import com.mycompany.domain.User;
import com.mycompany.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Override
	@Transactional
	public void save(User user) {
		String rawPwd = user.getPassword();
		user.setPassword(pwdEncoder.encode(rawPwd));
		user.setRole(Role.USER);
		userRepo.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}

}
