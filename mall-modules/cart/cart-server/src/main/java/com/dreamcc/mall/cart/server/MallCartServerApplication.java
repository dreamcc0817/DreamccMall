package com.dreamcc.mall.cart.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

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
@EnableFeignClients(basePackages = "com.dreamcc.mall.product.client")
public class MallCartServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MallCartServerApplication.class, args);
	}
}
