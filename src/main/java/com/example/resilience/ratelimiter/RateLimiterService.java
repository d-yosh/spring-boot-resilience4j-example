package com.example.resilience.ratelimiter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@Service
public class RateLimiterService {

    public String func() {
        return LocalDateTime.now().toString();
    }

    @RateLimiter(name = "limiterB")
    public String aop() {
        return LocalDateTime.now().toString();
    }
}
