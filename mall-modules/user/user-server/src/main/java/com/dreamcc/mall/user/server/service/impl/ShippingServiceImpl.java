package com.dreamcc.mall.user.server.service.impl;

import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.user.server.dao.ShippingDao;
import com.dreamcc.mall.user.server.entity.Shipping;
import com.dreamcc.mall.user.server.service.IShippingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.user.server.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/13 16:51
 * @Version: V1.0
 */
public class ShippingServiceImpl implements IShippingService {

	private ShippingDao shippingDao;

	@Autowired
	public ShippingServiceImpl(ShippingDao shippingDao) {
		this.shippingDao = shippingDao;
	}

	public ServerResponse add(Integer userId, Shipping shipping) {
		shipping.setUserId(userId);
		int rowCount = shippingDao.insert(shipping);
		if (rowCount > 0) {
			Map result = Maps.newHashMap();
			result.put("shippingId", shipping.getId());
			return ServerResponse.createBySuccess("add address success", result);
		}
		return ServerResponse.createByErrorMessage("add address failed");
	}

	public ServerResponse<String> del(Integer userId, Integer shippingId) {
		int resultCount = shippingDao.deleteByShippingIdUserId(userId, shippingId);
		if (resultCount > 0) {
			return ServerResponse.createBySuccess("delete address success");
		}
		return ServerResponse.createByErrorMessage("delete address failed");
	}


	public ServerResponse update(Integer userId, Shipping shipping) {
		shipping.setUserId(userId);
		int rowCount = shippingDao.updateByShipping(shipping);
		if (rowCount > 0) {
			return ServerResponse.createBySuccess("update address success");
		}
		return ServerResponse.createByErrorMessage("update address failed");
	}

	public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
		Shipping shipping = shippingDao.selectByShippingIdUserId(userId, shippingId);
		if (shipping == null) {
			return ServerResponse.createByErrorMessage("not found address");
		}
		return ServerResponse.createBySuccess("update address success", shipping);
	}

	public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Shipping> shippingList = shippingDao.selectByUserId(userId);
		PageInfo pageInfo = new PageInfo(shippingList);
		return ServerResponse.createBySuccess(pageInfo);
	}
}
