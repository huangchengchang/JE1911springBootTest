package com.hqyj.springBootTest.modules.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hqyj.springBootTest.modules.account.service.UserService;
import com.hqyj.springBootTest.modules.test.controller.TestController;

@Controller
@RequestMapping("/account")
public class AccountController {
	private final static Logger LOGGER =LoggerFactory.getLogger(TestController.class);
	private UserService us;
	
	/*
	 * localhost:8089/account/login
	 */
	@RequestMapping("/login")
	public String goLogin() {
		return "indexSimple";
	}
	/*
	 * localhost:8089/account/dashboard
	 */
	@RequestMapping("/dashboard")
	public String dashboardPag() {
		return "index";
	}
}
