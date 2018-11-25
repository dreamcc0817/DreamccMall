package com.dreamcc.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.entity
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/24 10:25
 * @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
	private int id;
	private int categoryId;
	private String name;
	private String subtitle;
	private String mainImages;
	private String subImages;
	private String detail;
	private BigDecimal price;
	private int stock;
	private int status;
	private Date createTime;
	private Date updateTime;
}
