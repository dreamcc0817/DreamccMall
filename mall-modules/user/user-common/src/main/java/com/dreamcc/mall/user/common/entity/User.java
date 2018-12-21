package com.dreamcc.mall.user.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@ApiModelProperty(hidden = true)
	private Integer id;

	@ApiModelProperty(required = true)
	private String username;

	private String password;

	private String email;

	private String phone;

	private String question;

	private String answer;

	private Integer role;

	@ApiModelProperty(hidden = true)
	private Date createTime;

	@ApiModelProperty(hidden = true)
	private Date updateTime;

}