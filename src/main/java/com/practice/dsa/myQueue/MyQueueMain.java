package com.practice.dsa.myQueue;

public class MyQueueMain {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(7);
        myQueue.enqueue(8);
        myQueue.enqueue(9);
        myQueue.printQueue();
        System.out.println("Removed node: "+myQueue.dequeue().value);
//        System.out.println("Removed node: "+myQueue.dequeue().value);
//        System.out.println("Removed node: "+myQueue.dequeue().value);
        myQueue.printQueue();
    }
}
