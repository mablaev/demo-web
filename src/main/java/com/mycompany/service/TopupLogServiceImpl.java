package com.mycompany.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.domain.TopupLog;
import com.mycompany.repo.TopupLogRepo;

@Service
public class TopupLogServiceImpl implements TopupLogService {
	@Autowired
	private TopupLogRepo topupLogRepo;

	@Override
	@Transactional(readOnly = true)
	public List<TopupLog> findAllUsersTopUps(int from, int legth, int order) {
		int page = from / legth;

		PageRequest request = new PageRequest(page, legth);

		Page<TopupLog> all = topupLogRepo.findAll(request);

		return all.getContent();
	}

	@Override
	@Transactional(readOnly = true)
	public long count() {
		return topupLogRepo.count();
	}

	@Override
	@Transactional
	public void topupFor(String userEmail, BigDecimal amount) {
		TopupLog topupLog = topupLogRepo.findTopupLogByUserEmail(userEmail);
		topupLog.setAmount(amount);
		topupLogRepo.save(topupLog);
	}

}
