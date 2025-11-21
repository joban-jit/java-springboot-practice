package com.practice.questions;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Fibonacci {
    //0, 1, 1, 2, 3, 5, 8, 13, 21
    // 0+1 = 1
    // 1+1=2
    // 1+2=3
    // 2+3=5

    public static void main(String[] args) {
        int n =2;
        normalWay(9);
        // recursiveWay
        IntStream.range(0,n).forEach(i-> System.out.print(recursiveWay(i)+ " "));
        System.out.println();
        myWay(0); //[]
        myWay(1); //[0]
        myWay(2);//[0, 1]
        myWay(5);//[0, 1, 1, 2, 3]
    }

    private static int recursiveWay(int n){
        //F(N) = F(N-1) + F(N-2)
        if (n<=1) {
            return n;
        }
        return recursiveWay(n-1)+recursiveWay(n-2);
    }

    private static void normalWay(int n) {
        int a = 0, b = 1;
        List<Integer> fib = new ArrayList<>();
        if (n==1) {
            fib.add(0);
        } else if (n>=2) {
            fib.add(0);
            fib.add(1);
        }
        for (int i = 2; i< n; i++){

            int c = a+b;
            fib.add(c);
            a = b;
            b = c;
        }
        System.out.println(fib);
    }

    private static void myWay(int n){
        int a = 0;
        int b = 1;
        List<Integer> l1 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            l1.add(a);
            int c = a+b;
            a = b;
            b = c;
        }
        System.out.println(l1);
    }

}
