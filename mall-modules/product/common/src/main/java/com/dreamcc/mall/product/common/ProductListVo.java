package com.dreamcc.mall.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.vo
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/24 11:14
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductListVo {
	private Integer id;
	private Integer categoryId;
	private String name;
	private String subtitle;
	private String mainImage;
	private BigDecimal price;
	private Integer status;
	private String imageHost;
}
