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
}
