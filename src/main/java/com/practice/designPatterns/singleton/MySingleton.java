package com.practice.designPatterns.singleton;

import java.time.LocalDate;


public enum MySingleton {
    INSTANCE;

    private int pastDays;

    public LocalDate getPastDate(){
        return LocalDate.now().minusDays(this.pastDays);
    }

    public void setPastDays(int value){
        this.pastDays = value;
    }
    public LocalDate getFutureDate(int futureDays){
        return LocalDate.now().plusDays(futureDays);
    }
}
