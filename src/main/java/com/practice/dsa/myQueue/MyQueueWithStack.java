package com.practice.dsa.myQueue;

import com.practice.dsa.myStack.MyStackWithArrayList;

public class MyQueueWithStack {
    MyStackWithArrayList<Integer> stack1;
    MyStackWithArrayList<Integer> stack2;

    public MyQueueWithStack() {
        stack1 = new MyStackWithArrayList<>();
        stack2 = new MyStackWithArrayList<>();
    }

    public void enqueue(int value) {

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(value);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

    }

    public Integer dequeue() {

        if (isEmpty()) {
            return null;
        } else {
            return stack1.pop();
        }
    }

    public int peek() {
        return stack1.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }
}
