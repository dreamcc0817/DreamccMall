package com.dreamcc.mall.configer;

import com.dreamcc.mall.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.configer
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/8/20 21:12
 * @Version: V1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/","/login.html","/asserts/**","/webjars/**","/swagger-resources/**");
	}


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/index.html").setViewName("login");
	}

	@Bean
	public LocaleResolver localeResolver(){
		return new MyLocaleResolver();
	}
}
