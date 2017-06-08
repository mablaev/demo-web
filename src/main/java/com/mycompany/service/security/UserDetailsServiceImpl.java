package com.mycompany.service.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.mycompany.domain.User user = userRepo.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(user.getRole().asAuthority());

		UserDetails userDetails = new User(user.getEmail(), user.getPassword(), authorities);

		return userDetails;
	}

}
