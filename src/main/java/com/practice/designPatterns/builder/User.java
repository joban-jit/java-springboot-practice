package com.practice.designPatterns.builder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {

    private final String firstName; // required
    private final String lastName;  // required
    private final int age;          // optional
    private final String phone;     // optional
    private final String address;   // optional

    private User(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private int age;
        private String phone;
        private String address;

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public User build(){
            return new User(this);
        }



    }
}

