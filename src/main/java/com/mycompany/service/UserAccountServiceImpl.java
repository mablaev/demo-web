package com.mycompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.domain.UserAccount;
import com.mycompany.repo.UserAccountRepo;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	private UserAccountRepo userAccRepo;

	@Override
	@Transactional(readOnly = true)
	public UserAccount findUserAccountByUserId(Long userId) {
		return userAccRepo.findUserAccountByUserId(userId);
	}

	@Override
	@Transactional
	public void save(UserAccount ua) {
		userAccRepo.save(ua);
	}

	@Override
	@Transactional(readOnly = true)
	public UserAccount findUserAccountByUserEmail(String email) {
		return userAccRepo.findUserAccountByUserEmail(email);
	}

	@Override
	public long count() {
		return userAccRepo.count();
	}

	@Override
	public List<UserAccount> findAllUserAccounts(int from, int legth, int orderColumn) {
		int page = from / legth;

		PageRequest request = new PageRequest(page, legth);

		Page<UserAccount> all = userAccRepo.findAll(request);
		return all.getContent();
	}

}
