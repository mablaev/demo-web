package com.mycompany.web;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.domain.UserAccount;
import com.mycompany.service.UserAccountService;
import com.mycompany.service.security.SecurityService;
import com.mycompany.web.json.DataRequest;
import com.mycompany.web.json.DataResponse;
import com.mycompany.web.json.dto.UserAccountDto;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountService userAccService;

	@Autowired
	private SecurityService sececurityService;

	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public ModelAndView balanace() {
		String loggedUserEmail = sececurityService.findLoggedInUserName();

		UserAccount foundAcc = userAccService.findUserAccountByUserEmail(loggedUserEmail);

		ModelAndView mv = new ModelAndView("balance");

		if (foundAcc == null) {
			mv.addObject("error", "You do not have any account, your balance is unknow");
		} else {
			mv.addObject("balance", foundAcc.getBalance());
		}
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ModelAndView accountsData() {
		return new ModelAndView("accounts");
	}

	@ResponseBody
	@RequestMapping(value = "/accounts-data", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public DataResponse<UserAccountDto> accountsData(DataRequest request) {

		List<UserAccount> foundAcc = userAccService.findAllUserAccounts(
				request.getStart(),
				request.getLength(),
				request.getOrderColumn());

		DataResponse<UserAccountDto> response = new DataResponse<>();
		long count = userAccService.count();
		response.setTotal(count);
		response.setRecordsFiltered(count);
		response.setAccounts(foundAcc.stream()
				.map(UserAccountDto::new)
				.collect(toList()));

		return response;
	}
}
