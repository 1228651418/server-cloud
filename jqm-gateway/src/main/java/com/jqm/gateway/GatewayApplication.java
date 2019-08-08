package com.jqm.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jqm.gateway.filter.ElapsedGatewayFilterFactory;

@SpringBootApplication
public class GatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
//	@Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        // @formatter:off
//        return builder.routes()
//                .route(r -> r.path("/carstrip/**")
//                             .filters(f -> f.stripPrefix(1)
//                                            .filter(new RateLimitByIpGatewayFilter(10, 1, Duration.ofSeconds(1)))
////                                            .filter(rateLimitByCpuGatewayFilter))
//                                            )
//                             .uri("lb://carstrip")
//                             .order(0)
//                             .id("carstrip_service")
//                )
//                .build();
//        // @formatter:on
//    }
	
	@Bean
    public ElapsedGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new ElapsedGatewayFilterFactory();
    }
	
//	@Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
//  public RemoteAddrKeyResolver remoteAddrKeyResolver() {
//      return new RemoteAddrKeyResolver();
//  }
	
}
