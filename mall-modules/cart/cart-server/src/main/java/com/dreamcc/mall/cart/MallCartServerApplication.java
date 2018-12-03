package com.dreamcc.mall.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.cart
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/29 22:41
 * @Version: V1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MallCartServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MallCartServerApplication.class, args);
	}
}
