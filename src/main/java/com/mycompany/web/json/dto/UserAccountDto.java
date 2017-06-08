package com.mycompany.web.json.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycompany.domain.UserAccount;
import com.mycompany.web.config.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAccountDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String balance;
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate registrationDate;

	public UserAccountDto(UserAccount uacc) {
		email = uacc.getUser().getEmail();
		registrationDate = uacc.getUser().getRegistrationDate();
		balance = uacc.getBalance().toPlainString();
	}
}
