package com.bridgelabz.customAnnotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Step 1: Define the annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {
}

// Step 2: Create a class with annotated methods
class TaskRunner {

    @LogExecutionTime
    public void fastTask() {
        for (int i = 0; i < 1000; i++) {
            int x = i * 2;
        }
    }

    @LogExecutionTime
    public void slowTask() {
        for (int i = 0; i < 1_000_000; i++) {
            int x = i * 2;
        }
    }

    public void normalTask() {
        for (int i = 0; i < 100_000; i++) {
            int x = i * 2;
        }
    }
}

// Step 3: Use reflection to invoke methods and log execution time
public class ExecutionTime {
    public static void main(String[] args) throws Exception {
        TaskRunner runner = new TaskRunner();
        Class<?> clazz = runner.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                long start = System.nanoTime();

                method.invoke(runner);  // Run the method

                long end = System.nanoTime();
                long duration = end - start;

                System.out.println("Method: " + method.getName() + " executed in " + duration + " nanoseconds");
            }
        }
    }
}
