package com.dreamcc.mall.cart.server.client;

import com.dreamcc.mall.cart.common.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.cart.client
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/3 21:02
 * @Version: V1.0
 */
@FeignClient("cart")
public interface CartClient {
	@GetMapping("/cart/getList")
	List<Cart> cartList(Integer userId);
}
