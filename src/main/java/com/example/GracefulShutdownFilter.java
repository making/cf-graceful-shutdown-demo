package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GracefulShutdownFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(GracefulShutdownFilter.class);
    AtomicInteger currentRequest = new AtomicInteger(0);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        currentRequest.incrementAndGet();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            currentRequest.decrementAndGet();
        }
    }

    @Override
    public void destroy() {
        log.info("Destroy -> {}", currentRequest);
        gracefulShutdown();
    }

    void gracefulShutdown() {
        for (int i = 0; i < 10; i++) {
            log.info("{} requests remains.", currentRequest);
            if (currentRequest.get() == 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
