package com.mycompany.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mycompany.domain.TopupLog;

public interface TopupLogRepo extends PagingAndSortingRepository<TopupLog, Long> {

	TopupLog findTopupLogByUserEmail(String userEmail);
}
