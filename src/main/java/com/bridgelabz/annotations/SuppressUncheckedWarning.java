package com.bridgelabz.annotations;

import java.util.ArrayList;
import java.util.List;

public class SuppressUncheckedWarning {

    @SuppressWarnings("unchecked") // Suppresses the unchecked cast warning
    public static void main(String[] args) {
        // Creating a raw ArrayList (no generic type provided)
        List rawList = new ArrayList();

        rawList.add("Java");
        rawList.add("Python");

        // Unsafe cast (unchecked warning normally appears here)
        List<String> stringList = rawList; // Normally this shows a warning

        for (String language : stringList) {
            System.out.println("Language: " + language);
        }
    }
}
