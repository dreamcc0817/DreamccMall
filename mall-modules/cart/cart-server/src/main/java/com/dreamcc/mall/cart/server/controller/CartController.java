package com.dreamcc.mall.cart.server.controller;


import com.dreamcc.mall.cart.server.service.ICartService;
import com.dreamcc.mall.cart.server.vo.CartVo;
import com.dreamcc.mall.common.common.Const;
import com.dreamcc.mall.common.common.ResponseCode;
import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.user.common.entity.User;
import com.dreamcc.mall.common.util.CookieUtil;
import com.dreamcc.mall.common.util.JsonUtil;
import com.dreamcc.mall.common.util.RedisShardedPoolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.cart.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/29 23:01
 * @Version: V1.0
 */
@Api(value = "API - CartController", description = "Cart Interface Module")
@RestController
@RequestMapping("/cart")
public class CartController {

	private ICartService iCartService;

	@Autowired
	public CartController(ICartService iCartService) {
		this.iCartService = iCartService;
	}

	@ApiOperation(value = "get cart list", notes = "the module is get cart list")
	@RequestMapping("/getList")
	public ServerResponse<CartVo> list(@ApiIgnore HttpServletRequest request) {
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("User not logged in");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iCartService.list(1);
	}

	@ApiOperation(value = "add cart", notes = "the module is add cart")
	@RequestMapping("/addCart")
	public ServerResponse<CartVo> add(@ApiIgnore HttpServletRequest request, Integer count, Integer productId) {
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("User not logged in");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iCartService.add(user.getId(), productId, count);
	}

	@ApiOperation(value = "update cart", notes = "the module is update cart")
	@RequestMapping("/updateCart")
	public ServerResponse<CartVo> update(@ApiIgnore HttpServletRequest request, Integer count, Integer productId) {
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("User not logged in");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iCartService.update(user.getId(), productId, count);
	}

	@ApiOperation(value = "delete cart", notes = "the module is delete cart")
	@RequestMapping("/deleteCart")
	public ServerResponse<CartVo> delete(@ApiIgnore HttpServletRequest request, String productIds) {
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("User not logged in");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iCartService.deleteProduct(user.getId(), productIds);
	}

	@ApiOperation(value = "select all cart", notes = "the module is select all cart")
	@RequestMapping("/selectAllCart")
	public ServerResponse<CartVo> selectAll(@ApiIgnore HttpServletRequest request) {
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("User not logged in");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iCartService.selectOrUnSelect(user.getId(), null, Const.Cart.CHECKED);
	}

	@ApiOperation(value = "unselect all cart", notes = "the module is unselect all cart")
	@RequestMapping("/unselectAllCart")
	public ServerResponse<CartVo> unselectAll(@ApiIgnore HttpServletRequest request) {
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("User not logged in");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iCartService.selectOrUnSelect(user.getId(), null, Const.Cart.UN_CHECKED);
	}

	@ApiOperation(value = "select part cart", notes = "the module is select part cart")
	@RequestMapping("/selectCart")
	public ServerResponse<CartVo> select(@ApiIgnore HttpServletRequest request, Integer productId) {
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("User not logged in");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iCartService.selectOrUnSelect(user.getId(), productId, Const.Cart.CHECKED);
	}

	@ApiOperation(value = "unselect cart", notes = "the module is unselect cart")
	@RequestMapping("/unselectCart")
	public ServerResponse<CartVo> unselect(@ApiIgnore HttpServletRequest request, Integer productId) {
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("User not logged in");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iCartService.selectOrUnSelect(user.getId(), productId, Const.Cart.UN_CHECKED);
	}

	@ApiOperation(value = "unselect cart", notes = "the module is unselect cart")
	@RequestMapping("/unselectCart")
	public ServerResponse<Integer> getCartProductCount(@ApiIgnore HttpServletRequest request) {
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("User not logged in");
		}
		String userJsonStr = RedisShardedPoolUtil.get(loginToken);
		User user = JsonUtil.stringToObj(userJsonStr, User.class);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}
		return iCartService.getCartProductCount(user.getId());
	}
}
