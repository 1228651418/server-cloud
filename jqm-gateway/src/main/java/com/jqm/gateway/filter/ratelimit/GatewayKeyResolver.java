package com.jqm.gateway.filter.ratelimit;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Mono;

//@Configuration
public class GatewayKeyResolver{

	/**
	 * IP限流
	 * @return
	 */
	@Bean
	public KeyResolver ipKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
	
	@Bean
	KeyResolver userKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
	}
}
