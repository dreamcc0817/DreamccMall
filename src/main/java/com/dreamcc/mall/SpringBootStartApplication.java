package com.dreamcc.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@MapperScan(value = "com.dreamcc.mall.mapper")
//@SpringBootApplication
public class SpringBootStartApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(SpringBootStartApplication.class);
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(SpringBootStartApplication.class, args);
	}
}