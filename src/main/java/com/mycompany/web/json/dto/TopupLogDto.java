package com.mycompany.web.json.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycompany.domain.TopupLog;
import com.mycompany.web.config.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TopupLogDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String adminEmail;
	private String userEmail;

	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate topupDate;

	private String amount;

	public TopupLogDto(TopupLog topup) {
		adminEmail = topup.getAdmin().getEmail();
		userEmail = topup.getUser().getEmail();
		topupDate = topup.getTopupDate();
		amount = topup.getAmount().toPlainString();
	}

}
