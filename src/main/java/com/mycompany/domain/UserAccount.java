package com.mycompany.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = { "id", "balance" })
@Entity
public class UserAccount {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private BigDecimal balance;

	protected UserAccount() {
		// hibernate needs this
	}

	public UserAccount(User user, BigDecimal balance) {
		this.user = user;
		this.balance = balance;
	}

}
