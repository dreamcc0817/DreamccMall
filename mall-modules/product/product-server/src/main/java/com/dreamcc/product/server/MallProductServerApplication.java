package com.dreamcc.product.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan({"com.dreamcc.mall.dao","com.dreamcc.mall.product.dao"})
public class MallProductServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MallProductServerApplication.class, args);
	}
}
