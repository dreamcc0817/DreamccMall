package com.dreamcc.mall.cart.server.dao;

import com.dreamcc.mall.cart.server.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.cart.dao
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/29 23:09
 * @Version: V1.0
 */
public interface CartDao {

	/**
	 * add shopping cart
	 *
	 * @param cart
	 * @return
	 */
	int insertCart(Cart cart);

	/**
	 * update shopping cart
	 *
	 * @param cart
	 * @return
	 */
	int updateCart(Cart cart);

	/**
	 * delete shopping cart
	 *
	 * @param cartId
	 * @return
	 */
	int deleteCart(int cartId);

	List<Cart> getCartList(Integer userId);

	int selectCartProductCheckedStatusByUserId(Integer userId);

	Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId")Integer productId);

	int deleteByUserIdProductIds(@Param("userId") Integer userId,@Param("productIdList")List<String> productIdList);

	int checkedOrUncheckedProduct(@Param("userId") Integer userId,@Param("productId")Integer productId,@Param("checked") Integer checked);

	int selectCartProductCount(@Param("userId") Integer userId);

	List<Cart> selectCheckedCartByUserId(Integer userId);

}

