package com.hqyj.springBootTest.modules.account.service;

import com.hqyj.springBootTest.modules.account.entity.User;
import com.hqyj.springBootTest.modules.test.vo.Result;

public interface UserService {

	User selectUser(String userName);
	Result login(User user);
	
	Result registerUser(User user);
}
