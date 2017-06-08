package com.mycompany.repo;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.domain.User;

public interface UserRepo extends CrudRepository<User, Long> {
	User findUserByEmail(String email);
}
