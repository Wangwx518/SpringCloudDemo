package com.wangwx.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName gatewatConfig
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/14 15:59
 * @Version 1.0
 **/
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_wangwx",r->r.path("/guonei")
                                                                                                .uri("http://news.baidu.com.guoehi"))
                                                                                                .build();
        return routes.build();
    }
}
