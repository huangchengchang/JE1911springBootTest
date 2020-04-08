package com.hqyj.springBootTest.modules.account.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.springBootTest.modules.account.dao.UserDao;
import com.hqyj.springBootTest.modules.account.entity.User;
import com.hqyj.springBootTest.modules.account.service.UserService;
import com.hqyj.springBootTest.modules.account.vo.Result;
import com.hqyj.springBootTest.modules.account.vo.Result.ResultStatus;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao ud;
	/**
	 * 注册
	 */
	@Override
	public Result insertUser(User user) {
		Result result = new Result(ResultStatus.SUCCESS.status,"");
		
		User user1 = ud.selectUserByUserName(user.getUserName());
		if (user1 != null) {
				result.setStatus(ResultStatus.FAILED.status);
				result.setMessage("该用户已存在！");
				return result;
		}
		
		user.setCreateDate(new Date());
		ud.adduser(user);
		
		return result;
	}

	@Override
	public User selectUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return ud.selectUserByUserName(userName);
	}
	
	
	/**
	 * 登录
	 */
	@Override
	public Result selectUser(User user) {
		Result result = new Result(ResultStatus.SUCCESS.status,"");
		
		User user2 = ud.selectUser(user);
		if (user2 == null) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage("账号或密码错误！");
			return result;
		}else {
			result.setObject(user2);
		}
		return result;
	}


}
