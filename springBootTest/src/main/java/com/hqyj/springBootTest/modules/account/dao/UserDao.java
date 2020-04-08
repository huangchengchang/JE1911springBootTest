package com.hqyj.springBootTest.modules.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hqyj.springBootTest.modules.account.entity.User;
import com.hqyj.springBootTest.modules.account.entity.UserRole;

@Repository
@Mapper
public interface UserDao {
	//添加用户
	@Insert("INSERT INTO m_user (user_name,password,create_date) "
			+ "values (#{userName},#{password},#{createDate})")
	@Options(useGeneratedKeys = true,keyColumn ="user_id",keyProperty ="userId" )
	void adduser(User user);
	//查询用户名是否存在
	@Select("select * from m_user where user_name = #{userName}")
	User selectUserByUserName(String userName);
	
	@Select("select * from m_user where user_name=#{userName} and password=#{password}")
	User selectUser(User user);
	
}
