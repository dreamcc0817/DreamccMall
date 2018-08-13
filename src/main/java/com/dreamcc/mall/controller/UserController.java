package com.dreamcc.mall.controller;

import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.entity.User;
import com.dreamcc.mall.service.IUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@ApiOperation(value="load...",notes = "this is login",response = String.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "username",value = "username",required = true,dataType = "string",paramType = "query")
						,@ApiImplicitParam(name = "password",value = "password",required = true,dataType = "string",paramType = "query")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}
	)
	@PostMapping
	@RequestMapping("/login")
	public ServerResponse<User> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
		ServerResponse<User> response = userService.login(username, password);
		return response;
	}
}