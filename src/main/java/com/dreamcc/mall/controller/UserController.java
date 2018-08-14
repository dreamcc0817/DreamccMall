package com.dreamcc.mall.controller;

import com.dreamcc.mall.common.Const;
import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.entity.User;
import com.dreamcc.mall.service.IUserService;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	private IUserService userService;

	@ApiOperation(value = "load...", notes = "this is login", response = String.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "username", value = "username", required = true, dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "string", paramType = "query")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}
	)
	@PostMapping("/login")
	public ServerResponse<User> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
		ServerResponse<User> response = userService.login(username, password);
		if (response.isSuccess()) {
			session.setAttribute(Const.CURRENT_USER, response.getData());
		}
		return response;
	}

	@ApiOperation(value = "regist...", notes = "the moudle is regist", response = String.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "username", value = "username", required = true, dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "email", value = "email", dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "phone", value = "phone", dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "question", value = "question", dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "answer", value = "answer", dataType = "string", paramType = "query")

	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}
	)
	@PostMapping("/regist")
	public ServerResponse<String> register(@Param("user") User user) {
		return userService.register(user);
	}

	@ApiOperation(value = "checkValid...", notes = "the moudle is checkValid", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}
	)
	@GetMapping("/checkValid")
	public ServerResponse<String> checkValid(String str, String type) {
		return userService.checkValid(str, type);
	}

	@ApiOperation(value = "getUserInfo...", notes = "the moudle is getUserInfo", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}
	)
	@GetMapping("/getUserInfo")
	public ServerResponse<User> getUserInfo(HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user != null) {
			return ServerResponse.createBySuccess(user);
		}
		return ServerResponse.createByErrorMessage("user is not logged in");
	}
}