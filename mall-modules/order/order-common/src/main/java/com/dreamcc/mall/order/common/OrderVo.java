package com.dreamcc.mall.order.common;

import com.dreamcc.mall.user.common.entity.ShippingVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.order.common
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/26 9:55
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo {

	private Long orderNo;

	private BigDecimal payment;

	private Integer paymentType;

	private String paymentTypeDesc;
	private Integer postage;

	private Integer status;


	private String statusDesc;

	private String paymentTime;

	private String sendTime;

	private String endTime;

	private String closeTime;

	private String createTime;

	private List<OrderItemVo> orderItemVoList;

	private String imageHost;

	private Integer shippingId;

	private String receiverName;

	private ShippingVo shippingVo;

}
