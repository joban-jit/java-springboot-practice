package com.practice.concurrency.generalConcepts;

public class UsingLambdaAsRunnable {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println("run :"+Thread.currentThread().getName() );
        });
        t.start();
        // t.run() if you do this then no thread will be created ,
        // we will get
        //main: main
        //run(): main
        System.out.println("main: "+Thread.currentThread().getName());
    }

    //main: main
    //run(): Thread-0
    //or
    //run(): Thread-0
    //main: main
}
