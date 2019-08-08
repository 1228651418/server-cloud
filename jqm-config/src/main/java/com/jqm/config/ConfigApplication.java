package com.jqm.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
	
	/**
	 * 拦截器注册
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<MyOncePerRequestFilter> myOncePerRequestFilterRegistration() {
		FilterRegistrationBean<MyOncePerRequestFilter> registration = new FilterRegistrationBean<MyOncePerRequestFilter>();
		registration.setFilter(new MyOncePerRequestFilter());
		registration.addUrlPatterns("/*");// 拦截路径
		registration.setName("MyOncePerRequestFilter");// 拦截器名称
		registration.setOrder(2);// 顺序
		return registration;
	}
}
