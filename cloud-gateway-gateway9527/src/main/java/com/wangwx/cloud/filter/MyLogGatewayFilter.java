package com.wangwx.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

/**
 * @ClassName MyLogGatewayFilter
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/14 16:47
 * @Version 1.0
 **/
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("***************come in MyLogGatewayFilter:"+new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
      if (uname == null) {
          log.info("*****用户名为空，非法用户*******");
          exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
          return exchange.getResponse().setComplete();
      }

        return chain.filter(exchange);
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
