package com.dreamcc.mall.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.cart.vo
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/29 23:26
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductVo {
	private Integer id;
	private Integer userId;
	private Integer productId;
	private Integer quantity;
	private String productName;
	private String productSubtitle;
	private String productMainImage;
	private BigDecimal productPrice;
	private Integer productStatus;
	private BigDecimal productTotalPrice;
	private Integer productStock;
	private Integer productChecked;
	private String limitQuantity;
}
