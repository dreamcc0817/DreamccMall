package com.dreamcc.mall.cart.server.service;

import com.dreamcc.mall.cart.common.CartVo;
import com.dreamcc.mall.common.common.ServerResponse;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.cart.service
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/29 23:10
 * @Version: V1.0
 */
public interface ICartService {

	ServerResponse<CartVo> list(Integer userId);

	ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

	ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

	ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

	ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked);

	ServerResponse<Integer> getCartProductCount(Integer userId);
}
