package com.practice.designPatterns.builder;

public class BuilderTest {
    public static void main(String[] args) {
        User build = new User.Builder()
                .lastName("Doe")
                .firstName("John")
                .age(12)
                .build();
        System.out.println(build);
        //User(firstName=John, lastName=Doe, age=12, phone=null, address=null)
    }
}
