package com.dreamcc.mall.product.client;

import com.dreamcc.mall.product.common.DecreaseStockInput;
import com.dreamcc.mall.product.common.ProductListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.product.client
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/27 14:48
 * @Version: V1.0
 */
@FeignClient(name = "product")
public interface ProductClient {
	@PostMapping("/product/listForOrder")
	List<ProductListVo> listForOrder(@RequestBody List<String> productIdList);

	@PostMapping("/product/decreaseStock")
	void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
