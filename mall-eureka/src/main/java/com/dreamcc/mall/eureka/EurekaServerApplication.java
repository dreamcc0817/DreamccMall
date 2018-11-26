package com.dreamcc.mall.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/26 21:07
 * @Version: V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaServer
public class EurekaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
