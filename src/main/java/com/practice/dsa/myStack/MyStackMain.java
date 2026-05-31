package com.practice.dsa.myStack;

import java.util.Stack;

public class MyStackMain {
    public static void main(String[] args) {
        MyStack myStack = new MyStack(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.printStack();
        System.out.println("Popped node value: "+myStack.pop().value);
        myStack.printStack();
    }


}
