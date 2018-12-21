package com.dreamcc.mall.order.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.order.server.entity
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/21 12:50
 * @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private Integer id;

	private Long orderNo;

	private Integer userId;

	private Integer shippingId;

	private BigDecimal payment;

	private Integer paymentType;

	private Integer postage;

	private Integer status;

	private Date paymentTime;

	private Date sendTime;

	private Date endTime;

	private Date closeTime;

	private Date createTime;

	private Date updateTime;
}