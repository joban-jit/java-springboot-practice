package com.practice.enums;

import lombok.Getter;

@Getter
public enum Operation implements Calculator{
    ADD("+"){
        @Override
        public double apply(double a, double b){
            return a+b;
        }
    },
    MULTIPLY("*"){
        @Override
        public double apply(double a, double b){
            return a*b;
        }
    };

//    public abstract double apply(double a, double b);
    private final String symbol;
    Operation(String symbol){
        this.symbol = symbol;
    }
    public static String describe(Operation op){
        return switch(op){
            case ADD-> "Addition operation";
            case MULTIPLY -> "Multiplication operation";
        };
    }

}

interface Calculator {
    double apply(double a, double b);
}
