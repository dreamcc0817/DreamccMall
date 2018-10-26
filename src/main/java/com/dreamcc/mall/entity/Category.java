package com.dreamcc.mall.entity;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

	private Integer id;

	private Integer parentId;

	private String name;

	private Boolean status;

	private Integer sortOrder;

	private Date createTime;

	private Date updateTime;

}
