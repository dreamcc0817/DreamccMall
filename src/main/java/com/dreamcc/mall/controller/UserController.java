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
import java.util.Map;

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

	private final IUserService userService;

	@Autowired
	private UserController(IUserService userService) {
		this.userService = userService;
	}

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
	public ServerResponse<User> login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String, Object> map, HttpSession session) {
		ServerResponse<User> response = userService.login(username, password);
		if (response.isSuccess()) {
			session.setAttribute(Const.CURRENT_USER, response.getData());
		}
		map.put("msg", response.getMsg());
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
	@ApiImplicitParams({@ApiImplicitParam(name = "str", value = "str", required = true, dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "type", value = "type", required = true, dataType = "string", paramType = "query")
	})
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
	@ApiImplicitParams({@ApiImplicitParam(name = "session", value = "session", required = true, dataType = "string", paramType = "query")
	})
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

	@ApiOperation(value = "forgetGetQuestion...", notes = "the moudle is forgetGetQuestion", response = String.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "username", value = "username", required = true, dataType = "string", paramType = "query")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}
	)
	@PostMapping("/forgetGetQuestion")
	public ServerResponse<String> forgetGetQuestion(String username) {
		return userService.selectQuestion(username);
	}

	@ApiOperation(value = "forgetCheckAnswer...", notes = "the moudle is forgetCheckAnswer", response = String.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "username", value = "username", required = true, dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "question", value = "question", required = true, dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "answer", value = "answer", required = true, dataType = "string", paramType = "query")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}
	)
	@PostMapping("/forgetCheckAnswer")
	public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
		return userService.checkAnswer(username, question, answer);
	}

	@ApiOperation(value = "forgetRestPassword...", notes = "the moudle is forgetRestPassword", response = String.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "username", value = "username", required = true, dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "passwordNew", value = "passwordNew", required = true, dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "forgetToken", value = "forgetToken", required = true, dataType = "string", paramType = "query")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}
	)
	@PostMapping("/forgetRestPassword")
	public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
		return userService.forgetResetPassword(username, passwordNew, forgetToken);
	}

	@ApiOperation(value = "resetPassword...", notes = "the moudle is resetPassword", response = String.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "str", value = "str", required = true, dataType = "string", paramType = "query")
			, @ApiImplicitParam(name = "type", value = "type", required = true, dataType = "string", paramType = "query")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}
	)
	@PostMapping("/resetPassword")
	public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorMessage("user is not logging");
		}
		return userService.resetPassword(passwordOld, passwordNew, user);
	}

	@GetMapping("/test")
	public String test() {
		return "success";
	}

}