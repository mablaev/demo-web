package com.mycompany.web.json;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DataRequest {

	private int start;
	private int length;
	private int orderColumn;
	private Map<String, String> search;

}
