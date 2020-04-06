package com.hqyj.springBootTest.modules.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hqyj.springBootTest.modules.account.entity.User;
import com.hqyj.springBootTest.modules.account.entity.UserRole;

@Repository
@Mapper
public interface UserDao {
	@Select("select user_name,password from m_user")
	User selectUser(String userName);
	
	@Select("select user_id,user_name,password from m_user where user_name = #{userName}")
	User selectUserByUserName(String userName);
	@Insert("INSERT INTO m_user values (null,#{userName},#{userPassword},null)")
	int adduser(User user);
	@Insert("INSERT into m_user_role values (null,#{userId},#{roleId})")
	int insertUserAndRole(UserRole userRole);
}
