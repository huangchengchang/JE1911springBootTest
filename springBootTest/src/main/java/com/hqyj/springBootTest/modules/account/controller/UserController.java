package com.hqyj.springBootTest.modules.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.springBootTest.modules.account.entity.User;
import com.hqyj.springBootTest.modules.account.service.UserService;
import com.hqyj.springBootTest.modules.test.controller.TestController;
import com.hqyj.springBootTest.modules.test.vo.Result;
@Controller
@RequestMapping("/user")
public class UserController {
	private final static Logger LOGGER =LoggerFactory.getLogger(TestController.class);
	private UserService us;
	
	
	@RequestMapping("/gologin")
	public String goLogin() {
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result login(@RequestBody User user,HttpServletRequest request) {
		System.out.println(user);
		Result result = us.login(user);
		request.getSession().setAttribute("user", user);
		return result;
	}
	
	
	@RequestMapping("/goregister")
	public String goRegister() {
		return "register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Result register(@RequestBody User user) {
		return us.registerUser(user);
	}
}
