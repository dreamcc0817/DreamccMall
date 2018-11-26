package com.dreamcc.mall.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.config
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/26 22:14
 * @Version: V1.0
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class,args);
	}
}
