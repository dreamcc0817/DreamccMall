package com.dreamcc.mall.service.impl;

import com.dreamcc.mall.common.Const;
import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.entity.User;
import com.dreamcc.mall.mapper.UserMapper;
import com.dreamcc.mall.service.IUserService;
import com.dreamcc.mall.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/8/11 16:11
 * @Version: V1.0
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public ServerResponse<User> login(String username, String password) {
		int count = userMapper.checkUsername(username);
		if (count == 0) {
			return ServerResponse.createByErrorMessage("user is not exist");
		}
		String md5password = MD5Utils.MD5EncodeUtf8ByYaml(password);
		User user = userMapper.selectLogin(username, md5password);
		if (user == null) {
			return ServerResponse.createByErrorMessage("wrong password entered");
		}
		//clear session password information
		user.setPassword(StringUtils.EMPTY);
		return ServerResponse.createBySuccess("login success", user);
	}

	@Override
	public ServerResponse<String> register(User user) {
		ServerResponse<String> validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		user.setRole(Const.Role.ROLE_CUSTOMER);
		user.setPassword(MD5Utils.MD5EncodeUtf8ByYaml(user.getPassword()));
		int resultCount = userMapper.register(user);
		if (resultCount == 0) {
			return ServerResponse.createByErrorMessage("register failed");
		}
		return ServerResponse.createBySuccessMessage("register success");
	}

	@Override
	public ServerResponse<String> checkValid(String str, String type) {
		if (StringUtils.isNotBlank(type)) {
			if (Const.USERNAME.equals(type)) {
				int resultCount = userMapper.checkUsername(str);
				if (resultCount > 1) {
					return ServerResponse.createByErrorMessage("username is exist");
				}
			}
			if (Const.EMAIL.equals(type)) {
				int resultCount = userMapper.checkEmail(str);
				if (resultCount > 1) {
					return ServerResponse.createByErrorMessage("email is exist");
				}
			}
		} else {
			return ServerResponse.createByErrorMessage("param error");
		}
		return ServerResponse.createBySuccessMessage("valid success");
	}
}
