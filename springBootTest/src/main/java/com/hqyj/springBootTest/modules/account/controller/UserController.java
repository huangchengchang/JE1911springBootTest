package com.hqyj.springBootTest.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.springBootTest.modules.account.entity.User;
import com.hqyj.springBootTest.modules.account.service.UserService;
import com.hqyj.springBootTest.modules.account.vo.Result;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService us;
	
	@PostMapping(value = "/register",consumes = "application/json")
	public Result register(@RequestBody User user) {
		return us.insertUser(user);
	}
	
	@PostMapping(value = "/login",consumes = "application/json")
	public Result login(@RequestBody User user) {
		return us.selectUser(user);
	}
	
}
