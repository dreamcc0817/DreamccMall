package com.dreamcc.mall.user.client;

import com.dreamcc.mall.user.common.entity.Shipping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.user.client
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/26 13:08
 * @Version: V1.0
 */
@FeignClient("shipping")
public interface ShippingClient {
	@GetMapping("/select/{shippingId}")
	Shipping getShippingById(@PathVariable Integer shippingId);
}
