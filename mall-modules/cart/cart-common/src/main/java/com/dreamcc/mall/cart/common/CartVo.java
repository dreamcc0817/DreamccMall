package com.dreamcc.mall.cart.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.cart.vo
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/29 23:25
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVo {
	private List<CartProductVo> cartProductVoList;
	private BigDecimal cartTotalPrice;
	private Boolean allChecked;
	private String imageHost;
}
