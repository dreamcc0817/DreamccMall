package com.dreamcc.mall.configer;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.configer
 * @Description: springboot swagger config
 * @Author: dreamcc
 * @Date: 2018/8/11 9:16
 * @Version: V1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${server.servlet.context-path}")
	private String pathMapping;

	private ApiInfo initApiInfo() {
		ApiInfo apiInfo = new ApiInfo("DreamccMall Project Platform API",//Title
				initContextInfo(),//Simple Description
				"1.0.0",//Version
				"",
				"team by dreamcc",//Editor
				"The Apache License, Version 2.0",//Link Display Font
				"http://www.dreamcc.com"//Web Links
		);

		return apiInfo;
	}

	private String initContextInfo() {
		StringBuffer sb = new StringBuffer();

		return sb.toString();
	}

	@Bean
	public Docket restfulApi(){
		System.out.println("http://localhost:8080" + pathMapping + "/swagger-ui.html");
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("dreamcc")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(true)
				.forCodeGeneration(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.dreamcc.mall.controller"))
				.paths(doFilteringRules())//PathSelectors.any()
				.build()
				.apiInfo(initApiInfo());
	}

	/**
	 * set filter regular
	 * filter support regex
	 * @return
	 */
	private Predicate<String> doFilteringRules() {
		return or(
				regex("/hello.*"),
				regex("/vehicles.*"),
				regex("/user.*")
		);
	}
}
