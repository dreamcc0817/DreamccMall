package com.dreamcc.mall.mapper;

import com.dreamcc.mall.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

	/**
	 * check username is exist
	 *
	 * @param username
	 * @return
	 */
	int checkUsername(@Param("username") String username);

	/**
	 * check email is exist
	 *
	 * @param email
	 * @return
	 */
	int checkEmail(@Param("email") String email);

	/**
	 * login operation
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	User selectLogin(@Param("username") String username, @Param("password") String password);

	/**
	 * user register
	 *
	 * @param user
	 * @return
	 */
	int register(User user);
}