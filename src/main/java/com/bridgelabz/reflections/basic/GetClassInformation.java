package com.bridgelabz.reflections.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetClassInformation {
    private String classField;
    public String name;
    public int age;
    private String password;

    public GetClassInformation(String classField, String name, int age, String password){
        this.name = name;
        this.classField = classField;
        this.age = age;
        this.password = password;
    }

    public void getName(){
        System.out.println("Name: " + name);
    }

    public int getAge(){
        return age;
    }

    public static void main(String[] args) {
        Class<GetClassInformation> clazz = GetClassInformation.class;
        Method[] methods = clazz.getDeclaredMethods(); // Gives Private + public
        Field[] fields = clazz.getFields(); // Gives only public
        Constructor<?>[] constructors = clazz.getConstructors(); // Gives only public

        System.out.println("Methods name: ");
        for(Method method : methods){
            System.out.println(method.getName());
        }

        System.out.println("Fields name: ");
        for(Field field :fields){
            System.out.println(field.getName());
        }

        System.out.println("Constructor: ");
        for (Constructor<?> cons : constructors) {
            System.out.println("Public Constructor: " + cons);
        }


    }
}