package com.bridgelabz.customAnnotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

// Step 1: Define the @JsonField annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

// Step 2: Create a User class using @JsonField
class User1 {
    @JsonField(name = "user_id")
    private int id;

    @JsonField(name = "user_name")
    private String name;

    @JsonField(name = "user_email")
    private String email;

    private String password; // Not annotated — won't be serialized

    public User1(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

// Step 3: JSON Serializer class
public class CustomJsonSerializer {

    public static void main(String[] args) {
        User1 user = new User1(101, "Ankit", "ankit@example.com", "secret123");

        String jsonOutput = serializeToJson(user);
        System.out.println(jsonOutput);
    }

    // Step 4: Method to convert object to JSON string
    public static String serializeToJson(Object obj) {
        Map<String, String> jsonElements = new HashMap<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true); // allow access to private fields

            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField annotation = field.getAnnotation(JsonField.class);
                String key = annotation.name();

                try {
                    Object value = field.get(obj);
                    jsonElements.put(key, String.valueOf(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Build JSON string
        StringBuilder jsonBuilder = new StringBuilder("{\n");
        for (Map.Entry<String, String> entry : jsonElements.entrySet()) {
            jsonBuilder.append("  \"")
                    .append(entry.getKey())
                    .append("\": \"")
                    .append(entry.getValue())
                    .append("\",\n");
        }

        // Remove trailing comma
        if (!jsonElements.isEmpty()) {
            jsonBuilder.setLength(jsonBuilder.length() - 2);
        }

        jsonBuilder.append("\n}");
        return jsonBuilder.toString();
    }
}
