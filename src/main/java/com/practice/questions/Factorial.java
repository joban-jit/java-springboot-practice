package com.practice.questions;

public class Factorial {
    public static void main(String[] args) {
        int n = 5;
        long fact = 1;
        for(int i=1;i<=n;i++){
            fact*=i;
        }
        System.out.println(fact);

        System.out.println(factorial(5));

    }
    static long factorial(int n){
            if (n<=1){
                return 1;
            }
        return n*factorial(n-1);
    }
}
