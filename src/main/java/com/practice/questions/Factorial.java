package com.practice.questions;

public class Factorial {
    public static void main(String[] args) {
        int n = 5;
        long fact = 1;
        for(int i=1;i<=n;i++){
            fact*=i;
        }
        System.out.println(fact);
//F(n) = F(1)*F(2)...F(n-1)*F(n)
        System.out.println(factorial(5));

    }
    static long factorial(int n){
            if (n<=1){
                return 1;
            }
        return n*factorial(n-1);
    }
}
