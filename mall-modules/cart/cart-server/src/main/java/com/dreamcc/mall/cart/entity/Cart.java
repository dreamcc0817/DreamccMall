package com.dreamcc.mall.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.cart.entity
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/29 23:13
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	private Integer id;

	private Integer userId;

	private Integer productId;

	private Integer quantity;

	private Integer checked;

	private Date createTime;

	private Date updateTime;

}