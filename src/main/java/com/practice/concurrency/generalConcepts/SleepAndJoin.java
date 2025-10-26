package com.practice.concurrency.generalConcepts;

public class SleepAndJoin implements Runnable{
    String[] timeStr = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

    @Override
    public void run() {
        for(int i=9;i>=0;i--){
            System.out.println(timeStr[i]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Thread timer = new Thread(new SleepAndJoin());
        System.out.println("Starting 10 second count down...");
        timer.start();
        try {
            timer.join(); // the 'main' thread must wait here until 'timer' thread completes executions
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Boom!!!");
    }
}
