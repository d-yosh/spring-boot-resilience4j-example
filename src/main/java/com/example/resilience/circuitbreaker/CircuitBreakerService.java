package com.example.resilience.circuitbreaker;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CircuitBreakerService {

    public String func(String str) {
        if (str == null) {
            throw new RuntimeException();
        }
        return "success!!";
    }

    @CircuitBreaker(name = "circuitB")
    public String aop(String str) {
        if (str == null) {
            throw new RuntimeException();
        }
        return "success!!";
    }
}
