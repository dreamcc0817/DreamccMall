package com.dreamcc.product.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.product.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/6 16:15
 * @Version: V1.0
 */
@RestController
public class TestController {
	@Value("${hello}")
	private String hello;

	@RequestMapping("/hello")
	public String from() {
		return this.hello;
	}
}
