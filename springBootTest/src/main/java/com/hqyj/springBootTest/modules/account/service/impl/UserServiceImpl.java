package com.hqyj.springBootTest.modules.account.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.springBootTest.modules.account.dao.UserDao;
import com.hqyj.springBootTest.modules.account.entity.User;
import com.hqyj.springBootTest.modules.account.entity.UserRole;
import com.hqyj.springBootTest.modules.account.service.UserService;
import com.hqyj.springBootTest.modules.test.util.MD5Util;
import com.hqyj.springBootTest.modules.test.vo.Result;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao ud;
	
	@Override
	public User selectUser(String userName) {
		// TODO Auto-generated method stub
		return ud.selectUser(userName);
	}

	@Override
	public Result login(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			subject.login(token);
			User u = (User) SecurityUtils.getSubject().getSession().getAttribute("u");
			if (u == null) {
				return new Result("500", "账号不存在！");
			}
			subject.checkRoles();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return new Result("500", "账号密码错误！");
		}
		return new Result("200", "success");
	}

	@Override
	public Result registerUser(User user) {
		// 判断该用户名是否已经存在
				User u = ud.selectUserByUserName(user.getUserName());
				if (u != null) {
					return new Result("400", "该用户名已存在！");
				} else {
					// 对密码加密
				String Password = new MD5Util().getPasswordByMD5(user.getPassword(), user.getUserName());
				user.setPassword(Password);
					int num = ud.adduser(user);
					if (num > 0) {
						System.out.println(user.getUserId() + "@@@@@@@@@@@@@@@@");
						UserRole ur = new UserRole();
						ur.setUserId(user.getUserId());
						ur.setRoleId(4);
						int num1 = ud.insertUserAndRole(ur);
						if (num1 > 0) {
							return new Result("200", "success");
						} else {
							return new Result("500", "角色初始化失败！");
						}

					} else {
						return new Result("500", "注册失败！");
					}
				}
	}


}
