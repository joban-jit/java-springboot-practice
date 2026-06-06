package com.practice.dsa.myBinarySearchTree;

public class MyBinarySearchTreeMain {
    public static void main(String[] args) {
        MyBinarySearchTree myBST = new MyBinarySearchTree();
        System.out.println("Able to insert 47: " + myBST.insert(47));
        System.out.println("Able to insert 21: " + myBST.insert(21));
        System.out.println("Able to insert 76: " + myBST.insert(76));
        System.out.println("Able to insert 18: " + myBST.insert(18));
        System.out.println("Able to insert 52: " + myBST.insert(52));
        System.out.println("Able to insert 82: " + myBST.insert(82));

        System.out.println("Able to insert 27: " + myBST.insert(27));

        System.out.println(myBST.root.left.right.value);
        myBST.printBinarySearchTree();
        System.out.println("Contains 18: " + myBST.contains(18));
        System.out.println("Contains 17: " + myBST.contains(17));
        System.out.println("Contains 76: " + myBST.contains(76));
        System.out.println("Contains 27: " + myBST.contains(27));
        System.out.println("Contains 100: " + myBST.contains(100));

    }
}
