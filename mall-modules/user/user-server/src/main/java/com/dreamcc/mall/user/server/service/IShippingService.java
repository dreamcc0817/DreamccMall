package com.dreamcc.mall.user.server.service;

import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.user.server.entity.Shipping;
import com.github.pagehelper.PageInfo;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.user.server.service
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/13 16:49
 * @Version: V1.0
 */
public interface IShippingService {
	ServerResponse add(Integer userId, Shipping shipping);
	ServerResponse<String> del(Integer userId, Integer shippingId);
	ServerResponse update(Integer userId, Shipping shipping);
	ServerResponse<Shipping> select(Integer userId, Integer shippingId);
	ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
