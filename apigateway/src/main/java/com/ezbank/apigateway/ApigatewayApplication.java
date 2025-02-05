package com.ezbank.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}


	@Bean
	public RouteLocator circuitBreakRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			.route(r -> r
				.path("/accounts/**")
				.filters( f -> f
					.rewritePath("/accounts/(?<remaining>.*)", "/${remaining}")
					.circuitBreaker( config -> config
						.setName("Accounts Circuit Breaker")
						.setFallbackUri("forward:/fallback"))
				)
				.uri( "lb://ACCOUNTS"))
			.route(r -> r
				.path("/cards/**")
				.filters( f -> f
					.rewritePath("/cards/(?<remaining>.*)", "/${remaining}")
					.circuitBreaker( config -> config
						.setName("Cards Circuit Breaker")
						.setFallbackUri("forward:/fallback"))
				)
				.uri( "lb://CARDS"))
			.route(r -> r
				.path("/loans/**")
				.filters( f -> f
					.rewritePath("/loans/(?<remaining>.*)", "/${remaining}")
					.circuitBreaker( config -> config
						.setName("Loans Circuit Breaker")
						.setFallbackUri("forward:/fallback"))
				)
				.uri( "lb://LOANS"))
			.build();
	}
}
