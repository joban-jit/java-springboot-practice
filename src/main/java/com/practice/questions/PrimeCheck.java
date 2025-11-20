package com.practice.questions;

import java.util.stream.IntStream;

public class PrimeCheck {
    public static void main(String[] args) {
        bruteForce_Basic(29);
        primeOptimized(5);
        primeWithModernJava(5);

    }

    private static void primeWithModernJava(int n){
        boolean isPrime = true;
        if(n<=1){
            isPrime=false;
        }
        isPrime = IntStream.rangeClosed(2, (int) Math.sqrt(n))
                .noneMatch(i -> n % i == 0);
        System.out.println(isPrime);


    }



    private static void primeOptimized(int n){
        boolean isPrime = true;
        if (n<=1){
            isPrime= false;
        }
        if(n==2 || n==3){
            isPrime = true;
        }
        // Eliminate even and multiples of 3
        if(n%2==0 || n%3==0){
            isPrime = false;
        }
        // Only check factors up to sqrt(n)

        for(int i = 5; i<Math.sqrt(n); i+=6){
            System.out.println("INside: "+n);
            // Check i and i + 2 (since primes > 3 are of form 6k ± 1)
            if(n%i==0 || n%(i+2)==0){
                isPrime=false;
            }
        }
        System.out.println(isPrime);

    }

    private static void bruteForce_Basic(int n) {
        boolean isPrime = true;
        if(n<=1){
            isPrime=false;
        }else{
            for(int i=2;i<n;i++){
                if(n%i==0){
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println(isPrime);
    }
}
