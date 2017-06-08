package com.mycompany.web.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.mycompany.domain.Role;

public class DemoSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {

		String url;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		if (authorities.contains(Role.ADMIN.asAuthority())) {
			url = "/accounts";
		} else if (authorities.contains(Role.USER.asAuthority())) {
			url = "/balance";
		} else {
			url = "/access-denied";
		}

		return url;
	}

}
