package com.jqm.gateway.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {

	public static final String API_URI = "/v2/api-docs";
	public static final String EUREKA_SUB_PRIX = "CompositeDiscoveryClient_";
	private final DiscoveryClientRouteDefinitionLocator routeLocator;

	public SwaggerProvider(DiscoveryClientRouteDefinitionLocator routeLocator) {
		this.routeLocator = routeLocator;
	}

	@Value("#{${swagger.api}}")
	private Map<String, String> api;

	@Override
	public List<SwaggerResource> get() {
		Map<String, SwaggerResource> resMaps = new HashMap<>();
		routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
			String name = routeDefinition.getId().substring(EUREKA_SUB_PRIX.length()).toLowerCase();
			if(api.containsKey(name) && !resMaps.containsKey(name)) {
				resMaps.put(name,swaggerResource(api.get(name),
						routeDefinition.getPredicates().get(0).getArgs().get("pattern").replace("/**", API_URI)));
			}
		});
		return new ArrayList<>(resMaps.values());
	}

	private SwaggerResource swaggerResource(String name, String location) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion("2.0");
		return swaggerResource;
	}

}
