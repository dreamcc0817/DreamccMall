package com.dreamcc.mall.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailVo {
	private Integer id;
	private Integer categoryId;
	private String name;
	private String subtitle;
	private String mainImage;
	private String subImages;
	private String detail;
	private BigDecimal price;
	private Integer stock;
	private Integer status;
	private String createTime;
	private String updateTime;
	private String imageHost;
	private Integer parentCategoryId;
}