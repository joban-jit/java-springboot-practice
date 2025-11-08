package com.practice.concurrency.generalConcepts;

public class ExecutorServicePractice {

    // the concurrency API abstracts thread management for us
    // i.e. it enables complex processing involving threads without us having to manage threads directly

    // The ExecutorService is an interface that provides services for the creation and management of threads
    // The Executors utility class provides static methods that return ExecutorService implementation.
    // A "Thread pool" is a set of reusable worker threads available to execute tasks
    // Single thread pool executor: a single thread is used; tasks are processed sequentially
    // Cached thread pool executor: creates new threads as needed and reuses threads that have become free; care needed
    // as the number of threads can become very large
    // Fixed thread pool executor: creates a fixed number of threads which is specified at the start

    // Callable<V> is very similar to a Runnable except that a Callable can return a result and throw a checked exception
    // both Runnable and Callable is Asynchronous
    // you can use submit method to submit a Runnable(you can also use execute) and Callable
    // for runnable: void execute(Runnable) and Future<?> submit(Runnable)
    // for callable: Future<T> submit(Callable<T>)

    // A Future<V> is used to obtain the results from a Callable's call() method
    // A Future<V> object represents the result of an asynchronous computation. Methods are provided to check if the
    // computation is complete : isDone() and to retrieve the result of that computation: get()
    // The result can only be retrieved using the method V get() when the computation has completed, blocking if necessary
    // until it is ready.


    public static void main(String[] args) {

    }
}
