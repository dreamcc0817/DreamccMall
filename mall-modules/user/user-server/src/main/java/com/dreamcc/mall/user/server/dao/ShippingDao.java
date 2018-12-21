package com.dreamcc.mall.user.server.dao;

import com.dreamcc.mall.user.server.entity.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.user.server.dao
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/13 16:46
 * @Version: V1.0
 */
public interface ShippingDao {

	int insert(Shipping record);

	Shipping selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(Shipping record);

	int deleteByShippingIdUserId(@Param("userId")Integer userId, @Param("shippingId") Integer shippingId);

	int updateByShipping(Shipping record);

	Shipping selectByShippingIdUserId(@Param("userId")Integer userId,@Param("shippingId") Integer shippingId);

	List<Shipping> selectByUserId(@Param("userId")Integer userId);

}
