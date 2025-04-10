package com.bridgelabz.customAnnotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Step 1: Define the @Todo annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

// Step 2: Apply the annotation to multiple methods
class ProjectModule {

    @Todo(task = "Implement login logic", assignedTo = "Alice", priority = "HIGH")
    public void login() {
        System.out.println("Login logic not implemented yet.");
    }

    @Todo(task = "Integrate payment gateway", assignedTo = "Bob")
    public void processPayment() {
        System.out.println("Payment gateway integration pending.");
    }

    public void logout() {
        System.out.println("Logout completed.");
    }
}

// Step 3: Use reflection to retrieve and print all @Todo annotations
public class PendingTasks {
    public static void main(String[] args) {
        Class<ProjectModule> clazz = ProjectModule.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Todo.class)) {
                Todo todo = method.getAnnotation(Todo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Task: " + todo.task());
                System.out.println("Assigned To: " + todo.assignedTo());
                System.out.println("Priority: " + todo.priority());
                System.out.println();
            }
        }
    }
}
