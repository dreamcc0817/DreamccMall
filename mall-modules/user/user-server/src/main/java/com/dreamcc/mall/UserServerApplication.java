package com.dreamcc.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.dreamcc.mall.dao")
@SpringBootApplication
public class UserServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServerApplication.class, args);
	}

}
