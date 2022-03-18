package com.mrlu.springcloud.config;

        import org.springframework.cloud.gateway.filter.GatewayFilter;
        import org.springframework.cloud.gateway.filter.factory.AddRequestParameterGatewayFilterFactory;
        import org.springframework.cloud.gateway.route.RouteLocator;
        import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

/**
 * @author 简单de快乐
 * @date 2021-10-19 22:05
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为path_route_springboot的路由规则，
     * 当访问地址为http://localhost:9527/projects/spring-boot时
     * 会自动转发到https://spring.io/projects/spring-boot
     *
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_route_springboot", r -> r.path("/projects/spring-boot")
                .uri("https://spring.io/")).build();

        return routes.build();
    }
    
}
