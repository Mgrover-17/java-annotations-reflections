package com.bridgelabz.customAnnotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Step 1: Define the custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH";
}

// Step 2: Create a class and apply the annotation to methods
class Service {

    @ImportantMethod(level = "CRITICAL")
    public void processPayment() {
        System.out.println("Processing payment...");
    }

    @ImportantMethod // default level = HIGH
    public void generateReport() {
        System.out.println("Generating report...");
    }

    public void helperMethod() {
        System.out.println("Helper method...");
    }
}

// Step 3: Use Reflection to detect and print annotated methods
public class MarkImportantMethods {
    public static void main(String[] args) {
        Class<Service> clazz = Service.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Importance Level: " + annotation.level());
                System.out.println();
            }
        }
    }
}
