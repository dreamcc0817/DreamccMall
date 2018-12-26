package com.dreamcc.mall.order.server.service;

import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.order.common.OrderVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.order.server.service
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/21 12:47
 * @Version: V1.0
 */
public interface IOrderService {
	ServerResponse pay(Long orderNo,Integer userId,String path);

	ServerResponse aliCallBack(Map<String,String> params);

	ServerResponse queryOrderPayStatus(Integer userId,Long orderId);

	ServerResponse createOrder(Integer userId,Integer shippingId);

	ServerResponse<String> cancel(Integer userId,Long orderId);

	ServerResponse<String> getOrderCartProduct(Integer userId);

	ServerResponse<OrderVo> getOrderDetail(Integer userId,Long orderNo);

	ServerResponse<PageInfo> getOrderList(Integer userId,int pageNum,int pageSize);

	ServerResponse<PageInfo> manageList(int pageNum,int pageSize);

	ServerResponse<OrderVo> manageDetail(Long orderNo);

	ServerResponse<PageInfo> manageSearch(Long orderNo,int pageNum,int pageSize);

	ServerResponse<String> manageSendGoods(Long orderNo);
}
