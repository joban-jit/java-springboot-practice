package com.practice.dsa.myGraph;

public class MyGraphMain {
    public static void main(String[] args) {
        MyGraph myGraph = new MyGraph();
        myGraph.addVertex("A");
        myGraph.addVertex("B");
        myGraph.addVertex("C");
        myGraph.addVertex("D");

        myGraph.addEdge("A", "B");
        myGraph.addEdge("A", "C");
        myGraph.addEdge("A", "D");
        myGraph.addEdge("B", "D");
        myGraph.addEdge("C", "D");

        myGraph.printGraph();
        myGraph.removeVertex("D");
        myGraph.printGraph();
    }
}
