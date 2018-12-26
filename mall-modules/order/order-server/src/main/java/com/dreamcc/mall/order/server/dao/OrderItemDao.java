package com.dreamcc.mall.order.server.dao;

import com.dreamcc.mall.order.server.entity.Order;
import com.dreamcc.mall.order.server.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.order.server.dao
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/21 14:46
 * @Version: V1.0
 */
public interface OrderItemDao {
	int insertOrderItem(OrderItem orderItem);

	int updateOrderItem(OrderItem orderItem);

	int deleteOrderItemByPrimaryKey(Integer id);

	Order selectOrderItemByPrimaryKey(Integer id);

	List<OrderItem> getByOrderNoUserId(@Param("orderNo")Long orderNo, @Param("userId")Integer userId);

	List<OrderItem> getByOrderNo(@Param("orderNo")Long orderNo);

	void batchInsert(@Param("orderItemList") List<OrderItem> orderItemList);
}
