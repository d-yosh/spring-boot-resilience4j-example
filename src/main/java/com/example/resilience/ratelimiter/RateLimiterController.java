package com.example.resilience.ratelimiter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.vavr.control.Try;

@RestController
@RequestMapping("/ratelimiter")
public class RateLimiterController {

    private final RateLimiter rateLimiter;
    private final RateLimiterService service;

    public RateLimiterController(RateLimiterRegistry registry, RateLimiterService service) {
        this.rateLimiter = registry.rateLimiter("limiterA");
        this.service = service;
    }

    @GetMapping("func")
    public String func() {
        return Try.ofSupplier(RateLimiter.decorateSupplier(rateLimiter, service::func))
                .recover(RequestNotPermitted.class, "Request Not Permitted!!").get();
    }

    @GetMapping("aop")
    public String aop() {
        return Try.of(service::aop).recover(RequestNotPermitted.class, "Request Not Permitted!!").get();
    }
}
