package com.dreamcc.mall.user.server.controller;

import com.dreamcc.mall.common.common.ResponseCode;
import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.common.util.CookieUtil;
import com.dreamcc.mall.common.util.JsonUtil;
import com.dreamcc.mall.common.util.RedisShardedPoolUtil;
import com.dreamcc.mall.user.common.entity.User;
import com.dreamcc.mall.user.server.entity.Shipping;
import com.dreamcc.mall.user.server.service.IShippingService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.user.server.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/13 17:02
 * @Version: V1.0
 */
@RestController
@RequestMapping("/shipping")
public class ShippingController {

	private IShippingService iShippingService;

	@Autowired
	public ShippingController(IShippingService iShippingService) {
		this.iShippingService = iShippingService;
	}

	@ApiOperation(value = "add", notes = "the module is add shipping")
	@PostMapping("/add")
	public ServerResponse add(@ApiIgnore HttpServletRequest httpServletRequest, Shipping shipping) {
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("not found user info");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iShippingService.add(user.getId(), shipping);
	}

	@ApiOperation(value = "delete", notes = "the module is delete shipping")
	@DeleteMapping("/del")
	public ServerResponse del(@ApiIgnore HttpServletRequest httpServletRequest, Integer shippingId) {
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("not found user info");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);

		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iShippingService.del(user.getId(), shippingId);
	}

	@PutMapping("/update")
	public ServerResponse update(@ApiIgnore HttpServletRequest httpServletRequest, Shipping shipping) {
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("not found user info");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iShippingService.update(user.getId(), shipping);
	}


	@GetMapping("/select/{shippingId}")
	public ServerResponse<Shipping> select(@ApiIgnore HttpServletRequest httpServletRequest, @PathVariable Integer shippingId) {
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("not found user info");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);

		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iShippingService.select(user.getId(), shippingId);
	}


	@GetMapping("/list")
	public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
	                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
	                                     @ApiIgnore HttpServletRequest httpServletRequest) {

		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("not found user info");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);

		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iShippingService.list(user.getId(), pageNum, pageSize);
	}
}
