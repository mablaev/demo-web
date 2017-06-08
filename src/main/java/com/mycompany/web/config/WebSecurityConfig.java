package com.mycompany.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mycompany.domain.Role;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		   http.csrf().disable()
           	   .authorizeRequests()
               .antMatchers("/resources/**", "/registration").permitAll()
               .antMatchers("/admin/**", "/accounts", "/accounts-data").hasRole(Role.ADMIN.name())
               .anyRequest().authenticated()
               .and()
           .formLogin()
               .loginPage("/login")
               .successHandler(successHandler())
               .failureUrl("/login?error")
               .permitAll()
               .and()
           .logout().logoutUrl("/login?logout").deleteCookies("JSESSIONID").permitAll();

		//@formatter:off
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new DemoSuccesHandler();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}