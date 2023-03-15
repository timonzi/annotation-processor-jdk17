package org.example;

public class Main {
    public static void main(String[] args) {
        final var object = new MyClass();
        System.out.println(">> " + object.getClass().getDeclaredFields()[0].getAnnotations()[0]);
    }
}