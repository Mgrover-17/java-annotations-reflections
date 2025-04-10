package com.bridgelabz.customAnnotations;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

// Step 1: Define the @CacheResult annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {}

// Step 2: Create a class with an expensive method
class ExpensiveCalculator {
    private final Map<Integer, Long> cache = new HashMap<>();

    @CacheResult
    public long computeFibonacci(int n) {
        if (cache.containsKey(n)) {
            System.out.println("Returning cached value for " + n);
            return cache.get(n);
        }

        System.out.println("Computing Fibonacci for " + n);
        long result = fibonacci(n);
        cache.put(n, result);
        return result;
    }

    // Actual Fibonacci logic (inefficient for demonstration purposes)
    private long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

// Step 3: Main class to test caching
public class CustomCacheDemo {
    public static void main(String[] args) throws Exception {
        ExpensiveCalculator calculator = new ExpensiveCalculator();

        System.out.println("Result: " + calculator.computeFibonacci(10)); // First time (compute)
        System.out.println("Result: " + calculator.computeFibonacci(10)); // Cached
        System.out.println("Result: " + calculator.computeFibonacci(8));  // Compute
        System.out.println("Result: " + calculator.computeFibonacci(8));  // Cached
    }
}
