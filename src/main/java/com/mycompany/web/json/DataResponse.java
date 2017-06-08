package com.mycompany.web.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "accounts")
public class DataResponse<T> {

	@JsonProperty("draw")
	private int draw;

	@JsonProperty("recordsTotal")
	private long total;

	@JsonProperty("recordsFiltered")
	private long recordsFiltered;

	@JsonProperty
	private String error;

	@JsonProperty("data")
	private List<T> accounts = new ArrayList<>();

}
