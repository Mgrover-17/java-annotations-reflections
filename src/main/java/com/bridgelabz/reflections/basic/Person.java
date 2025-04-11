package com.bridgelabz.reflections.basic;

import java.lang.reflect.Field;

public class Person {
    public String name;
    private int age;
    private String password;
    public String email;

    public Person(String name, int age, String password, String email){
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public int getAge(){
        return age;
    }

    public static void main(String[] args) {
        Person person = new Person("manvi", 22, "manvi@1234" , "manvi@gmail.com");
        Class<?> clazz = person.getClass();
        Field[] fields = clazz.getDeclaredFields(); // Gives private + public

        for (Field field : fields){
            if(field.getName().equals("age")) {
                field.setAccessible(true);
                try {
                    field.set(person, 22);
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Age: " + person.getAge());
    }
}