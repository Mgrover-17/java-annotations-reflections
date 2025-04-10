package com.bridgelabz.annotations;

public class MarkOldMethod {

    @Deprecated
    public void oldFeature() {
        System.out.println("⚠️ Warning: oldFeature() is deprecated and should not be used.");
    }

    public void newFeature() {
        System.out.println("✅ newFeature() is the recommended method.");
    }

    public static void main(String[] args) {
        MarkOldMethod api = new MarkOldMethod();

        // Calling the deprecated method
        api.oldFeature();  // You'll see a warning here in most IDEs (like IntelliJ or Eclipse)

        // Calling the new method
        api.newFeature();
    }
}
