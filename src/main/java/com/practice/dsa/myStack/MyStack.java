package com.practice.dsa.myStack;

import com.practice.dsa.mylinkedlist.LinkedList;

public class MyStack {


    class Node{
        int value;
        Node next;
        Node(int value){
            this.value = value;
        }
    }

    private Node top;
    private int height;

    public MyStack (int value){
        this.top = new Node(value);
        height = 1;
    }

    public void printStack(){
        System.out.println("------");
        if(top==null || height==0){
            System.out.println("Stack is empty");
        }else{
            System.out.println("Height: "+height);
            System.out.println("Top: "+top.value);
            Node temp = top;
            while(temp!=null){
                System.out.println("Value: "+temp.value);
                temp = temp.next;
            }
        }
        System.out.println("------");
    }

    public void push(int value){
        Node newNode = new Node(value);
        if(this.top!=null){
            newNode.next = this.top;
        }
        this.top = newNode;
        height++;
    }

    public Node pop(){
        if(this.top==null){
            return null;
        }else{
            Node temp = this.top;
            this.top = this.top.next;
            temp.next = null;
            height--;
            return temp;
        }
    }




}
