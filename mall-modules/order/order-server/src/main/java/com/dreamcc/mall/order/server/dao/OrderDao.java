package com.dreamcc.mall.order.server.dao;

import com.dreamcc.mall.order.server.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.order.server.dao
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/21 11:48
 * @Version: V1.0
 */
public interface OrderDao {
	int insertOrder(Order order);

	int updateOrder(Order order);

	int deleteOrderByPrimaryKey(Integer id);

	Order selectOrderByPrimaryKey(Integer id);

	Order selectOrderByUserIdAndOrderNo(@Param("userId")Integer userId,@Param("orderId")Long orderNo);

	Order selectByOrderId(Long orderId);

	List<Order> selectByUserId(Integer userId);

	List<Order> selectAllOrder();

}
