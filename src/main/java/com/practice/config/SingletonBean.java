package com.practice.config;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

public class SingletonBean {

    @Autowired
    private PrototypeBean prototypeBean;

    public SingletonBean() {
        System.out.println("Singleton instance created");
    }

    public PrototypeBean getPrototypeBean() {
        System.out.println(String.valueOf(LocalTime.now()));
        return prototypeBean;
    }
}
