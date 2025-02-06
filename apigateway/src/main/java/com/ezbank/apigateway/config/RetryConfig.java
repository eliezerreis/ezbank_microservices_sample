package com.ezbank.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.Duration;

@Configuration
public class RetryConfig {

    @Bean
    public RouteLocator circuitBreakRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r
                .path("/accounts/**")
                .filters(f -> f
                    .rewritePath("/accounts/(?<remaining>.*)", "/${remaining}")

                    // Implement exponential backoff in case the HTTP Get Methods failed
                    .retry(config -> config
                        .setRetries(3)
                        .setSeries(HttpStatus.Series.SERVER_ERROR, HttpStatus.Series.CLIENT_ERROR)
                        .setStatuses(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.BAD_REQUEST, HttpStatus.NOT_FOUND)
                        .setMethods(HttpMethod.GET)
                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
                    )

                    // Implement circuit breaker to gracefully handle failures of the system
                    // The circuit breaker has timeout configurations to handle slowness of the system
                    .circuitBreaker(config -> config
                        .setName("Accounts Circuit Breaker")
                        .setFallbackUri("forward:/fallback"))
                )
                .uri("lb://ACCOUNTS"))

            .route(r -> r
                .path("/cards/**")
                .filters(f -> f
                    .rewritePath("/cards/(?<remaining>.*)", "/${remaining}")
                    .circuitBreaker(config -> config
                        .setName("Cards Circuit Breaker")
                        .setFallbackUri("forward:/fallback"))
                )
                .uri("lb://CARDS"))

            .route(r -> r
                .path("/loans/**")
                .filters(f -> f
                    .rewritePath("/loans/(?<remaining>.*)", "/${remaining}")
                    .circuitBreaker(config -> config
                        .setName("Loans Circuit Breaker")
                        .setFallbackUri("forward:/fallback"))
                )
                .uri("lb://LOANS"))
            .build();
    }
}
