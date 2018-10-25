package com.dreamcc.mall.controller;

import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.entity.User;
import com.dreamcc.mall.service.IUserService;
import com.dreamcc.mall.util.CookieUtil;
import com.dreamcc.mall.util.JsonUtil;
import com.dreamcc.mall.util.RedisPoolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/8/11 16:02
 * @Version: V1.0
 */
@Api(value = "API - UserController", description = "User Interface Module")
@RestController
@RequestMapping("/user")
public class UserController {

	private IUserService userService;

	@Autowired
	private UserController(IUserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "/login", notes = "the moudle is login", response = String.class)
	@PostMapping("/login")
	public ServerResponse<User> login(@RequestParam("username") String username, @RequestParam("password") String password, @ApiIgnore HttpSession session, @ApiIgnore HttpServletResponse httpServletResponse) {
		ServerResponse<User> response = userService.login(username, password);
		if (response.isSuccess()) {
			CookieUtil.writeLoginToken(httpServletResponse, session.getId());
		}
		return response;
	}

	public ServerResponse<String> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		CookieUtil.delLoginToken(httpServletRequest, httpServletResponse);
		return ServerResponse.createBySuccess();
	}

	@ApiOperation(value = "register", notes = "the moudle is regist")
	@PostMapping("/regist")
	public ServerResponse<String> register(@ModelAttribute("user") @ApiParam(name = "user") User user) {
		return userService.register(user);
	}

	@ApiOperation(value = "check valid", notes = "the moudle is check valid", response = String.class)
	@GetMapping("/checkValid")
	public ServerResponse<String> checkValid(String str, String type) {
		return userService.checkValid(str, type);
	}

	@ApiOperation(value = "get user info", notes = "the moudle is get user info", response = String.class)
	@GetMapping("/getUserInfo")
	public ServerResponse<User> getUserInfo(@ApiIgnore HttpServletRequest httpServletRequest) {
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("user is not logged in");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user != null) {
			return ServerResponse.createBySuccess(user);
		}
		return ServerResponse.createByErrorMessage("user is not logged in");
	}

	@ApiOperation(value = "forget get question", notes = "the moudle is forget get question", response = String.class)
	@PostMapping("/forgetGetQuestion")
	public ServerResponse<String> forgetGetQuestion(String username) {
		return userService.selectQuestion(username);
	}

	@ApiOperation(value = "forget checkAnswer", notes = "the moudle is forget check answer", response = String.class)
	@PostMapping("/forgetCheckAnswer")
	public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
		return userService.checkAnswer(username, question, answer);
	}

	@ApiOperation(value = "forget rest password", notes = "the moudle is forget rest password", response = String.class)
	@PostMapping("/forgetRestPassword")
	public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
		return userService.forgetResetPassword(username, passwordNew, forgetToken);
	}

	@ApiOperation(value = "reset password", notes = "the moudle is reset password", response = String.class)
	@PostMapping("/resetPassword")
	public ServerResponse<String> resetPassword(@ApiIgnore HttpServletRequest httpServletRequest, String passwordOld, String passwordNew) {
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("user is not logged in");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorMessage("user is not logging");
		}
		return userService.resetPassword(passwordOld, passwordNew, user);
	}
}