package com.dreamcc.mall.order.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.order.common
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/26 9:59
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemVo {

	private Long orderNo;

	private Integer productId;

	private String productName;

	private String productImage;

	private BigDecimal currentUnitPrice;

	private Integer quantity;

	private BigDecimal totalPrice;

	private String createTime;

}
