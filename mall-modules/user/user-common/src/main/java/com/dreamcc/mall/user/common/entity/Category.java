package com.dreamcc.mall.user.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.entity
 * @Description:
 * @Author: dreamcc
 * @Date: 2018-10-26 10:47
 * @Version: V1.0
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

	@ApiModelProperty(hidden = true)
	private Integer id;

	@ApiModelProperty(required = true, hidden = true)
	private Integer parentId;

	@ApiModelProperty(required = true)
	private String name;

	private Boolean status;

	private Integer sortOrder;

	private Date createTime;

	private Date updateTime;

	public Category(Integer parentId, String name) {
		this.parentId = parentId;
		this.name = name;
	}
}
