package com.mycompany.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

	GUEST, USER, ADMIN;

	public GrantedAuthority asAuthority() {
		return new SimpleGrantedAuthority("ROLE_" + name());
	}

}
