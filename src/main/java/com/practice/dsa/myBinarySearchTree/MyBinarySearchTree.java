package com.practice.dsa.myBinarySearchTree;

public class MyBinarySearchTree {
    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    Node root;

    public MyBinarySearchTree(int value) {
        this.root = new Node(value);
    }

    public MyBinarySearchTree() {

    }

    public Node getRoot() {
        return this.root;
    }

    public void printBinarySearchTree() {

        System.out.println("Tree Structure:");
        printTree(root, "", false);
    }


    private void printTree(Node node, String prefix, boolean isLeft) {
        if (node == null) return;
        printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.value);
        printTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
    }

    public boolean insert(int value) {
        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
            return true;
        }
        Node temp = root;
        while (true) {
            if (newNode.value == temp.value) {
                return false;
            }
            if (newNode.value < temp.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                } else {
                    temp = temp.left;
                }
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                } else {
                    temp = temp.right;
                }
            }
        }

    }

    public boolean contains(int value) {
        Node temp = root;
        while (temp != null) {
            if (value < temp.value) {
                temp = temp.left;
            } else if (value > temp.value) {
                temp = temp.right;
            } else {
                return true;
            }
        }
        return false;
    }


}
