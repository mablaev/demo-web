package com.mycompany.web;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.domain.TopupLog;
import com.mycompany.service.TopupLogService;
import com.mycompany.web.json.DataRequest;
import com.mycompany.web.json.DataResponse;
import com.mycompany.web.json.dto.TopupLogDto;

@Controller
public class TopupContoller {

	@Autowired
	private TopupLogService topupLogService;

	@ResponseBody
	@RequestMapping(value = "/topup-log", method = RequestMethod.GET)
	public ModelAndView topupLog() {
		return new ModelAndView("topup-log");
	}

	@ResponseBody
	@RequestMapping(value = "/topup", method = RequestMethod.GET)
	public void topup(String userEmail, BigDecimal amount) {
		System.out.println(userEmail);
		topupLogService.topupFor(userEmail, amount);
	}

	@ResponseBody
	@RequestMapping(value = "/topup-log-data", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public DataResponse<TopupLogDto> topupLogData(DataRequest request) {

		List<TopupLog> allTopups = topupLogService.findAllUsersTopUps(request.getStart(),
				request.getLength(),
				request.getOrderColumn());

		long total = topupLogService.count();

		DataResponse<TopupLogDto> response = new DataResponse<>();
		response.setTotal(total);
		response.setRecordsFiltered(total);
		response.setAccounts(allTopups.stream()
				.map(TopupLogDto::new)
				.collect(toList()));

		return response;
	}
}
