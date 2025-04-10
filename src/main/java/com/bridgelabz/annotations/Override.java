package com.bridgelabz.annotations;

public class Override {
    public static void main(String[] args) {
        Dog dog=new Dog();
        dog.makeSound();
    }
}
class Animal{
    void makeSound(){
        System.out.println("makes sound");
    }
}

class Dog extends Animal{

    @java.lang.Override
    void makeSound(){
        System.out.println("dog barks");
    }
}
