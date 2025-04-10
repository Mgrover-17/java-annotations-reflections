package com.bridgelabz.customAnnotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;

// Step 1: Create the annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// Step 2: Apply annotation to the User class
class User {
    @MaxLength(10)
    private String username;

    public User(String username) {
        this.username = username;

        validateMaxLength();
    }

    private void validateMaxLength() {
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                field.setAccessible(true);
                try {
                    String value = (String) field.get(this);
                    if (value != null && value.length() > maxLength.value()) {
                        throw new IllegalArgumentException(
                                "Field '" + field.getName() + "' exceeds max length of " + maxLength.value()
                        );
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "User{username='" + username + "'}";
    }
}

// Step 3: Main class to test
public class MaxLengthAnnotation {
    public static void main(String[] args) {
        // Valid username
        User user1 = new User("John123");
        System.out.println(user1);

        // Invalid username (exceeds 10 characters)
        User user2 = new User("ThisIsTooLong");
        System.out.println(user2);  // Won't reach here
    }
}
