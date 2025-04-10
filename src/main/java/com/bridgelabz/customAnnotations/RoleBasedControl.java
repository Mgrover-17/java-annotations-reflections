package com.bridgelabz.customAnnotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Step 1: Create the annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
    String value();
}

// Step 2: Simulate user context
class UserContext {
    private static String currentUserRole;

    public static void setRole(String role) {
        currentUserRole = role;
    }

    public static String getRole() {
        return currentUserRole;
    }
}

// Step 3: Service with restricted methods
class AdminService {

    @RoleAllowed("ADMIN")
    public void deleteUser() {
        System.out.println("User deleted successfully!");
    }

    @RoleAllowed("USER")
    public void viewDashboard() {
        System.out.println("Dashboard displayed.");
    }
}

// Step 4: Access control executor
public class RoleBasedControl {

    public static void main(String[] args) throws Exception {
        UserContext.setRole("USER");  // Change this to "ADMIN" to allow admin access

        AdminService service = new AdminService();
        executeIfAuthorized(service, "deleteUser");
        executeIfAuthorized(service, "viewDashboard");
    }

    // Helper to check role and invoke method
    public static void executeIfAuthorized(Object obj, String methodName) throws Exception {
        Method method = obj.getClass().getMethod(methodName);

        if (method.isAnnotationPresent(RoleAllowed.class)) {
            RoleAllowed annotation = method.getAnnotation(RoleAllowed.class);
            String requiredRole = annotation.value();
            String currentUserRole = UserContext.getRole();

            if (requiredRole.equals(currentUserRole)) {
                method.invoke(obj);
            } else {
                System.out.println("Access Denied! Role '" + currentUserRole + "' cannot access '" + methodName + "'");
            }
        } else {
            // If no annotation, assume allowed
            method.invoke(obj);
        }
    }
}
