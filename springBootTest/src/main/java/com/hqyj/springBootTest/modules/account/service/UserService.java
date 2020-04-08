package com.hqyj.springBootTest.modules.account.service;

import com.hqyj.springBootTest.modules.account.entity.User;
import com.hqyj.springBootTest.modules.account.vo.Result;

public interface UserService {
	
	Result insertUser(User user);
	User selectUserByUserName(String userName);
	Result selectUser(User user);
}
