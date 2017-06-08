package com.mycompany.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.BaseTest;
import com.mycompany.domain.TopupLog;

public class TopupLogServiceTest extends BaseTest {

	@Autowired
	private TopupLogService topupLogService;

	@Test
	public void paginateAllTopups() {
		List<TopupLog> allTopups = topupLogService.findAllUsersTopUps(0, 10, 0);
		assertThat(allTopups.size(), is(1));
	}
}
