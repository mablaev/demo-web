package com.mycompany.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mycompany.domain.UserAccount;

public interface UserAccountRepo extends PagingAndSortingRepository<UserAccount, Long> {
	UserAccount findUserAccountByUserId(Long userId);

	UserAccount findUserAccountByUserEmail(String email);
}
