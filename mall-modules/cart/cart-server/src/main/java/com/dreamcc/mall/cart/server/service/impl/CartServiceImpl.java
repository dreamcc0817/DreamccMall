package com.dreamcc.mall.cart.server.service.impl;

import com.dreamcc.mall.cart.server.dao.CartDao;
import com.dreamcc.mall.cart.server.entity.Cart;
import com.dreamcc.mall.cart.server.service.ICartService;
import com.dreamcc.mall.cart.server.vo.CartProductVo;
import com.dreamcc.mall.cart.server.vo.CartVo;
import com.dreamcc.mall.common.Const;
import com.dreamcc.mall.common.ResponseCode;
import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.product.server.client.ProductClient;
import com.dreamcc.mall.product.server.entity.Product;
import com.dreamcc.mall.util.BigDecimalUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.cart.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/29 23:10
 * @Version: V1.0
 */
@Service("cartService")
public class CartServiceImpl implements ICartService {

	private ProductClient productClient;

	private CartDao cartDao;

	@Autowired
	public CartServiceImpl(ProductClient productClient, CartDao cartDao) {
		this.productClient = productClient;
		this.cartDao = cartDao;
	}

	@Override
	public ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count) {
		if (productId == null || count == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		Cart cart = cartDao.selectCartByUserIdProductId(userId, productId);
		if (cart == null) {
			Cart cartItem = new Cart();
			cartItem.setQuantity(count);
			cartItem.setChecked(Const.Cart.CHECKED);
			cartItem.setProductId(productId);
			cartItem.setUserId(userId);
			cartDao.insertCart(cartItem);
		} else {
			count = cart.getQuantity() + count;
			cart.setQuantity(count);
			cartDao.updateCart(cart);
		}
		return this.list(userId);
	}

	@Override
	public ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count) {
		if (productId == null || count == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		Cart cart = cartDao.selectCartByUserIdProductId(userId, productId);
		if (cart != null) {
			cart.setQuantity(count);
		}
		cartDao.updateCart(cart);
		return this.list(userId);
	}

	@Override
	public ServerResponse<CartVo> deleteProduct(Integer userId, String productIds) {
		List<String> productList = Splitter.on(",").splitToList(productIds);
		if (CollectionUtils.isEmpty(productList)) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		cartDao.deleteByUserIdProductIds(userId, productList);
		return this.list(userId);
	}

	@Override
	public ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked) {
		cartDao.checkedOrUncheckedProduct(userId, productId, checked);
		return this.list(userId);
	}

	@Override
	public ServerResponse<Integer> getCartProductCount(Integer userId) {
		if (userId == null) {
			return ServerResponse.createBySuccess(0);
		}
		return ServerResponse.createBySuccess(cartDao.selectCartProductCount(userId));
	}

	@Override
	public ServerResponse<CartVo> list(Integer userId) {
		CartVo cartVo = this.getCartVoLimit(userId);
		return ServerResponse.createBySuccess(cartVo);
	}

	private CartVo getCartVoLimit(Integer userId) {
		CartVo cartVo = new CartVo();
		List<Cart> cartList = cartDao.getCartList(userId);
		List<CartProductVo> cartProductVoList = Lists.newArrayList();
		//init cart total price,this need bigdecimal to avoid losing accuracy
		BigDecimal cartTotalPrice = new BigDecimal("0");

		if (CollectionUtils.isNotEmpty(cartList)) {
			for (Cart cartItem : cartList) {
				CartProductVo cartProductVo = new CartProductVo();
				cartProductVo.setId(cartItem.getId());
				cartProductVo.setUserId(userId);
				cartProductVo.setProductId(cartItem.getProductId());

				Product product = productClient.getPorudctById(cartItem.getProductId());
				if (product != null) {
					cartProductVo.setProductName(product.getName());
					cartProductVo.setProductSubtitle(product.getSubtitle());
					cartProductVo.setProductStatus(product.getStatus());
					cartProductVo.setProductPrice(product.getPrice());
					cartProductVo.setProductStock(product.getStock());

					int buyLimitCount = 0;
					if (product.getStock() >= cartItem.getQuantity()) {
						buyLimitCount = cartItem.getQuantity();
						//limit purchase quantity
						cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
					} else {
						buyLimitCount = product.getStock();
						//limit purchase quantity
						cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
						Cart cartForQuantity = new Cart();
						cartForQuantity.setId(cartItem.getId());
						cartForQuantity.setQuantity(buyLimitCount);
						cartDao.updateCart(cartForQuantity);
					}
					cartProductVo.setQuantity(buyLimitCount);
					cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(), cartProductVo.getQuantity()));
					cartProductVo.setProductChecked(cartItem.getChecked());
				}
				if (cartItem.getChecked() == Const.Cart.CHECKED) {
					cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
				}
				cartProductVoList.add(cartProductVo);
			}
		}
		cartVo.setCartTotalPrice(cartTotalPrice);
		cartVo.setCartProductVoList(cartProductVoList);
		cartVo.setAllChecked(this.getAllCheckedStatus(userId));
		return cartVo;
	}

	private boolean getAllCheckedStatus(Integer userId) {
		if (userId == null) {
			return false;
		}
		return cartDao.selectCartProductCheckedStatusByUserId(userId) == 0;

	}
}
