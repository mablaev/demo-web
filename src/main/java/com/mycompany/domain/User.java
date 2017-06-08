package com.mycompany.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = { "id", "email", "role" })
@Entity
@Table(name = "DEMO_USER")
@BatchSize(size = 100)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, insertable = false)
	private LocalDate registrationDate = LocalDate.now();

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	protected User() {
		//hibernate needs this
	}

	public User(String email, String password, Role role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}
}
