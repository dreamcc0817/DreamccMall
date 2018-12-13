package com.dreamcc.mall.service;

import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.entity.User;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.service
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/8/11 16:03
 * @Version: V1.0
 */
public interface IUserService {
	/**
	 *  login by username and password
	 * @param username
	 * @param password
	 * @return
	 */
	ServerResponse<User> login(String username, String password);

	/**
	 * user register
	 * @param user
	 * @return
	 */
	ServerResponse<String> register(User user);

	/**
	 * check user is valid
	 * @param str
	 * @param type
	 * @return
	 */
	ServerResponse<String> checkValid(String str, String type);

	/**
	 * select Question check user identity
	 * @param username
	 * @return
	 */
	ServerResponse<String> selectQuestion(String username);

	/**
	 * update user password by answer mapping question
	 * @param username
	 * @param question
	 * @param answer
	 * @return
	 */
	ServerResponse<String> checkAnswer(String username, String question, String answer);

	/**
	 *
	 * @param username
	 * @param passwordNew
	 * @param token
	 * @return
	 */
	ServerResponse<String> forgetResetPassword(String username, String passwordNew, String token);

	/**
	 * rest password by username
	 * @param passwordOld
	 * @param passwordNew
	 * @param user
	 * @return
	 */
	ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

	/**
	 * update userinfo
	 * @param user
	 * @return
	 */
	ServerResponse<User> updateInformation(User user);

	/**
	 * get user info
	 * @param userId
	 * @return
	 */
	ServerResponse<User> getInformation(Integer userId);

	/**
	 * check user role is administrator
	 * @param user
	 * @return
	 */
	ServerResponse checkAdminRole(User user);

}
