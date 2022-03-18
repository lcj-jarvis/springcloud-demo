package com.mrlu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 简单de快乐
 * @date 2021-10-20 22:20
 *
 * 自定义网关的Filter
 */
// @Component
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    /**
     * @param exchange ServerWebExchange有点类似HttpServletRequest
     * @param chain
     * @return Mono有点类似于ModelAndView
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("请求的URI:" + exchange.getRequest().getURI());
        System.out.println("请求的IP:" + exchange.getRequest().getRemoteAddress());
        System.out.println("请求的路径:" + exchange.getRequest().getPath());

        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username == null || "".equals(username)) {
            System.out.println("用户名不能为null或者空串,无法登录");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 过滤器的顺序，有点类似于优先级，数字越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
