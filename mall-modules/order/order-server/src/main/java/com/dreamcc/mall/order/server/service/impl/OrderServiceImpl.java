package com.dreamcc.mall.order.server.service.impl;

import com.dreamcc.mall.cart.common.Cart;
import com.dreamcc.mall.cart.server.client.CartClient;
import com.dreamcc.mall.common.common.Const;
import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.common.util.BigDecimalUtil;
import com.dreamcc.mall.common.util.DateTimeUtil;
import com.dreamcc.mall.order.common.OrderItemVo;
import com.dreamcc.mall.order.common.OrderVo;
import com.dreamcc.mall.order.server.dao.OrderDao;
import com.dreamcc.mall.order.server.dao.OrderItemDao;
import com.dreamcc.mall.order.server.entity.Order;
import com.dreamcc.mall.order.server.entity.OrderItem;
import com.dreamcc.mall.order.server.service.IOrderService;
import com.dreamcc.mall.product.client.ProductClient;
import com.dreamcc.mall.user.client.ShippingClient;
import com.dreamcc.mall.user.common.entity.Shipping;
import com.dreamcc.mall.user.common.entity.ShippingVo;
import com.dreamcc.product.server.entity.Product;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.order.server.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/21 12:47
 * @Version: V1.0
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	private OrderDao orderDao;
	private OrderItemDao orderItemDao;
	private CartClient cartClient;
	private ProductClient productClient;
	private ShippingClient shippingClient;

	@Autowired
	public OrderServiceImpl(OrderDao orderDao, OrderItemDao orderItemDao, CartClient cartClient,ShippingClient shippingClient) {
		this.orderDao = orderDao;
		this.orderItemDao = orderItemDao;
		this.cartClient = cartClient;
		this.shippingClient = shippingClient;
	}

	@Override
	public ServerResponse pay(Long orderNo, Integer userId, String path) {
		return null;
	}

	@Override
	public ServerResponse aliCallBack(Map<String, String> params) {
		return null;
	}

	@Override
	public ServerResponse queryOrderPayStatus(Integer userId, Long orderId) {
		return null;
	}

	@Override
	public ServerResponse createOrder(Integer userId, Integer shippingId) {
		//get shopping cart data
		List<Cart> cartList = cartClient.cartList(userId);
		ServerResponse serverResponse = this.getCartOrderItem(userId, cartList);
		if(!serverResponse.isSuccess()){
			return serverResponse;
		}
		List<OrderItem> orderItemList = (List<OrderItem>)serverResponse.getData();
		BigDecimal payment = this.getOrderTotalPrice(orderItemList);
		Order order = this.assembleOrder(userId,shippingId,payment);
		if(order == null){
			return ServerResponse.createByErrorMessage("order is error");
		}
		if(CollectionUtils.isEmpty(orderItemList)){
			return ServerResponse.createByErrorMessage("shopping cart is empty");
		}
		for(OrderItem orderItem:orderItemList){
			orderItem.setOrderNo(order.getOrderNo());
		}
		orderItemDao.batchInsert(orderItemList);

		this.reduceProductStock(orderItemList);

		this.cleanCart(cartList);

		OrderVo orderVo = assembleOrderVo(order,orderItemList);
		
		return ServerResponse.createBySuccess(orderVo);
	}

	@Override
	public ServerResponse<String> cancel(Integer userId, Long orderId) {
		return null;
	}

	@Override
	public ServerResponse<String> getOrderCartProduct(Integer userId) {
		return null;
	}

	@Override
	public ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo) {
		return null;
	}

	@Override
	public ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize) {
		return null;
	}

	@Override
	public ServerResponse<PageInfo> manageList(int pageNum, int pageSize) {
		return null;
	}

	@Override
	public ServerResponse<OrderVo> manageDetail(Long orderNo) {
		return null;
	}

	@Override
	public ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize) {
		return null;
	}

	@Override
	public ServerResponse<String> manageSendGoods(Long orderNo) {
		return null;
	}

	private ServerResponse getCartOrderItem(Integer userId, List<Cart> cartList) {
		List<OrderItem> orderItemList = Lists.newArrayList();
		if (CollectionUtils.isEmpty(orderItemList)) {
			return ServerResponse.createByErrorMessage("shopping cart is empty");
		}
		for (Cart cartItem : cartList) {
			OrderItem orderItem = new OrderItem();
			Product product = productClient.getPorudctById(cartItem.getProductId());
			if (Const.ProductStatusEnum.ON_SALE.getCode() != product.getStatus()) {
				return ServerResponse.createByErrorMessage("product " + product.getName() + " is not sell ");
			}
			if (cartItem.getQuantity() > product.getStock()) {
				return ServerResponse.createByErrorMessage("product" + product.getName() + "is understock");
			}
			orderItem.setUserId(userId);
			orderItem.setProductId(product.getId());
			orderItem.setProductName(product.getName());
			orderItem.setCurrentUnitPrice(product.getPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(), cartItem.getQuantity()));
			orderItemList.add(orderItem);
		}
		return ServerResponse.createBySuccess(orderItemList);
	}

	private BigDecimal getOrderTotalPrice(List<OrderItem> orderItemList){
		BigDecimal payment = new BigDecimal("0");
		for(OrderItem orderItem : orderItemList){
			payment = BigDecimalUtil.add(payment.doubleValue(),orderItem.getTotalPrice().doubleValue());
		}
		return payment;
	}

	private OrderVo assembleOrderVo(Order order,List<OrderItem> orderItemList){
		OrderVo orderVo = new OrderVo();
		orderVo.setOrderNo(order.getOrderNo());
		orderVo.setPayment(order.getPayment());
		orderVo.setPaymentType(order.getPaymentType());
		orderVo.setPaymentTypeDesc(Const.PaymentTypeEnum.codeOf(order.getPaymentType()).getValue());

		orderVo.setPostage(order.getPostage());
		orderVo.setStatus(order.getStatus());
		orderVo.setStatusDesc(Const.OrderStatusEnum.codeOf(order.getStatus()).getValue());
		orderVo.setShippingId(order.getShippingId());
		Shipping shipping = shippingClient.getShippingById(order.getShippingId());
		if(shipping != null){
			orderVo.setReceiverName(shipping.getReceiverName());
			orderVo.setShippingVo(assembleShippingVo(shipping));
		}

		orderVo.setPaymentTime(DateTimeUtil.dateToStr(order.getPaymentTime()));
		orderVo.setSendTime(DateTimeUtil.dateToStr(order.getSendTime()));
		orderVo.setEndTime(DateTimeUtil.dateToStr(order.getEndTime()));
		orderVo.setCreateTime(DateTimeUtil.dateToStr(order.getCreateTime()));
		orderVo.setCloseTime(DateTimeUtil.dateToStr(order.getCloseTime()));

		List<OrderItemVo> orderItemVoList = Lists.newArrayList();

		for(OrderItem orderItem : orderItemList){
			OrderItemVo orderItemVo = assembleOrderItemVo(orderItem);
			orderItemVoList.add(orderItemVo);
		}
		orderVo.setOrderItemVoList(orderItemVoList);
		return orderVo;
	}
	private OrderItemVo assembleOrderItemVo(OrderItem orderItem){
		OrderItemVo orderItemVo = new OrderItemVo();
		orderItemVo.setOrderNo(orderItem.getOrderNo());
		orderItemVo.setProductId(orderItem.getProductId());
		orderItemVo.setProductName(orderItem.getProductName());
		orderItemVo.setProductImage(orderItem.getProductImage());
		orderItemVo.setCurrentUnitPrice(orderItem.getCurrentUnitPrice());
		orderItemVo.setQuantity(orderItem.getQuantity());
		orderItemVo.setTotalPrice(orderItem.getTotalPrice());

		orderItemVo.setCreateTime(DateTimeUtil.dateToStr(orderItem.getCreateTime()));
		return orderItemVo;
	}

	private ShippingVo assembleShippingVo(Shipping shipping){
		ShippingVo shippingVo = new ShippingVo();
		shippingVo.setReceiverName(shipping.getReceiverName());
		shippingVo.setReceiverAddress(shipping.getReceiverAddress());
		shippingVo.setReceiverProvince(shipping.getReceiverProvince());
		shippingVo.setReceiverCity(shipping.getReceiverCity());
		shippingVo.setReceiverDistrict(shipping.getReceiverDistrict());
		shippingVo.setReceiverMobile(shipping.getReceiverMobile());
		shippingVo.setReceiverZip(shipping.getReceiverZip());
		shippingVo.setReceiverPhone(shippingVo.getReceiverPhone());
		return shippingVo;
	}

	private Order assembleOrder(Integer userId,Integer shippingId,BigDecimal payment){
		Order order = new Order();
		long orderNo = this.generateOrderNo();
		order.setOrderNo(orderNo);
		order.setStatus(Const.OrderStatusEnum.NO_PAY.getCode());
		order.setPostage(0);
		order.setPaymentType(Const.PaymentTypeEnum.ONLINE_PAY.getCode());
		order.setPayment(payment);

		order.setUserId(userId);
		order.setShippingId(shippingId);
		int rowCount = orderDao.insertOrder(order);
		if(rowCount > 0){
			return order;
		}
		return null;
	}

	private long generateOrderNo(){
		long currentTime =System.currentTimeMillis();
		return currentTime+new Random().nextInt(100);
	}

	/**
	 * reduce product stock
	 * @param orderItemList
	 */
	private void reduceProductStock(List<OrderItem> orderItemList) {
		for (OrderItem orderItem : orderItemList) {

		}
	}

	/**
	 * cleanCart
	 * @param cartList
	 */
	private void cleanCart(List<Cart> cartList) {

	}

}
