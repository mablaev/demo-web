package com.mycompany.service;

import java.math.BigDecimal;
import java.util.List;

import com.mycompany.domain.TopupLog;

public interface TopupLogService {

	List<TopupLog> findAllUsersTopUps(int from, int legth, int order);

	void topupFor(String userEmail, BigDecimal amount);

	long count();

}
