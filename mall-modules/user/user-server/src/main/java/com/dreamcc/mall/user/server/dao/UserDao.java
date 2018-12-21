package com.dreamcc.mall.user.server.dao;

import com.dreamcc.mall.user.common.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

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

	/**
	 * select question by username
	 * @param username
	 * @return
	 */
	String selectQuestionByUsername(String username);

	/**
	 * check answer and question is matching
	 * @param username
	 * @param question
	 * @param answer
	 * @return
	 */
	int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

	/**
	 * update password by username
	 * @param username
	 * @param passwordNew
	 * @return
	 */
	int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

	/**
	 * check old password is correct
	 * @param password
	 * @param userId
	 * @return
	 */
	int checkPassword(@Param(value = "password") String password, @Param("userId") Integer userId);

	/**
	 * check email by user id
	 * @param email
	 * @param userId
	 * @return
	 */
	int checkEmailByUserId(@Param(value = "email") String email, @Param(value = "userId") Integer userId);

	/**
	 * update user info
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(User record);
}