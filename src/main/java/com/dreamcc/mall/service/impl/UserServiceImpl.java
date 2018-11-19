package com.dreamcc.mall.service.impl;

import com.dreamcc.mall.common.Const;
import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.common.TokenCache;
import com.dreamcc.mall.entity.User;
import com.dreamcc.mall.dao.UserDao;
import com.dreamcc.mall.service.IUserService;
import com.dreamcc.mall.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
	private UserDao userDao;

	@Override
	public ServerResponse<User> login(String username, String password) {
		int count = userDao.checkUsername(username);
		if (count == 0) {
			return ServerResponse.createByErrorMessage("user is not exist");
		}
		String md5password = MD5Utils.MD5EncodeUtf8ByYaml(password);
		User user = userDao.selectLogin(username, md5password);
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
		int resultCount = userDao.register(user);
		if (resultCount == 0) {
			return ServerResponse.createByErrorMessage("register failed");
		}
		return ServerResponse.createBySuccessMessage("register success");
	}

	@Override
	public ServerResponse<String> checkValid(String str, String type) {
		if (StringUtils.isNotBlank(type)) {
			if (Const.USERNAME.equals(type)) {
				int resultCount = userDao.checkUsername(str);
				if (resultCount > 0) {
					return ServerResponse.createByErrorMessage("username is exist");
				}
			}
			if (Const.EMAIL.equals(type)) {
				int resultCount = userDao.checkEmail(str);
				if (resultCount > 0) {
					return ServerResponse.createByErrorMessage("email is exist");
				}
			}
		} else {
			return ServerResponse.createByErrorMessage("param error");
		}
		return ServerResponse.createBySuccessMessage("valid success");
	}

	@Override
	public ServerResponse<String> selectQuestion(String username) {
		ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
		if (validResponse.isSuccess()) {
			return ServerResponse.createByErrorMessage("user is not exist");
		}
		String question = userDao.selectQuestionByUsername(username);
		if (org.apache.commons.lang3.StringUtils.isNotBlank(question)) {
			return ServerResponse.createBySuccess(question);
		}
		return ServerResponse.createByErrorMessage("rest password question is null");
	}

	@Override
	public ServerResponse<String> checkAnswer(String username, String question, String answer) {
		int restCount = userDao.checkAnswer(username, question, answer);
		if (restCount > 0) {
			String forgetToken = UUID.randomUUID().toString();
			TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
			return ServerResponse.createBySuccess(forgetToken);
		}
		return ServerResponse.createByErrorMessage("answer is error");
	}

	@Override
	public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
		if (StringUtils.isBlank(forgetToken)) {
			return ServerResponse.createByErrorMessage("param is error ,param need token");
		}
		ServerResponse<String> validResponse = this.checkValid(username, Const.USERNAME);
		if (validResponse.isSuccess()) {
			return ServerResponse.createByErrorMessage("user is not exist");
		}
		String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
		if (StringUtils.isBlank(token)) {
			return ServerResponse.createByErrorMessage("token is error");
		}
		if (org.apache.commons.lang3.StringUtils.equals(forgetToken, token)) {
			String md5Password = MD5Utils.MD5EncodeUtf8ByYaml(passwordNew);
			int rowCount = userDao.updatePasswordByUsername(username, md5Password);
			if (rowCount > 0) {
				return ServerResponse.createBySuccessMessage("update password is success");
			}
		} else {
			return ServerResponse.createByErrorMessage("token error,please attach new token");
		}
		return ServerResponse.createByErrorMessage("update password is failed");
	}

	@Override
	public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
		int resultCount = userDao.checkPassword(MD5Utils.MD5EncodeUtf8ByYaml(passwordOld),user.getId());
		if(resultCount == 0){
			return ServerResponse.createByErrorMessage("旧密码错误");
		}

		user.setPassword(MD5Utils.MD5EncodeUtf8ByYaml(passwordNew));
		int updateCount = userDao.updateByPrimaryKeySelective(user);
		if(updateCount > 0){
			return ServerResponse.createBySuccessMessage("密码更新成功");
		}
		return ServerResponse.createByErrorMessage("密码更新失败");
	}

	@Override
	public ServerResponse<User> updateInformation(User user) {
		return null;
	}

	@Override
	public ServerResponse<User> getInformation(Integer userId) {
		return null;
	}

	@Override
	public ServerResponse checkAdminRole(User user) {
		return null;
	}
}
