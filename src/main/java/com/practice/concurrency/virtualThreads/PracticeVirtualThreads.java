package com.practice.concurrency.virtualThreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static com.practice.util.Util.sleep;

public class PracticeVirtualThreads {
    //  When to Use Virtual Threads
    //  High-concurrency applications (e.g., servers handling thousands of connections).
    //  I/O-bound tasks (HTTP requests, database queries).
    //  NOT ideal for CPU-intensive tasks (since they still run on OS threads).

    //  Main Differences Recap
    //  Platform threads = OS-level threads (limited).
    //  Virtual threads = JVM-managed, lightweight, millions possible.
    //  Both implement java.lang.Thread API → easy migration.
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        usingStartVirtualThread();
        System.out.println(usingNewVirtualThreadPerTaskExecutor());
//        usingStartVirtualThreadManyVirtualThread();
    }

    private static void usingStartVirtualThread() {
//        FYI:
        // In platform threads, Thread.sleep() or I/O operations block an OS thread → expensive.
        // In virtual threads, blocking releases the underlying carrier thread back to the pool → cheap blocking.
        Thread.startVirtualThread(()->{
            System.out.println("usingStartVirtualThread: hello from Virtual Thread: "+Thread.currentThread());
        });

        sleep(100); // give thread time to run
        System.out.println("usingStartVirtualThread: Main thread completed");
    }

    private static void usingStartVirtualThreadManyVirtualThread(){
        IntStream.range(0,100000).forEach(i->{
            Thread.startVirtualThread(()->{
                sleep(1000); // This does NOT block an OS thread
                System.out.print(i+",");
            });
        });
        System.out.println("Starting 100,000 virtual threads!");
        sleep(4000); // Wait to keep JVM alive and letting it complete

    }

    private static String usingNewVirtualThreadPerTaskExecutor() throws ExecutionException, InterruptedException {
        // Executors.newVirtualThreadPerTaskExecutor() creates an executor that assigns a new virtual thread per task.

        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
            Future<String> future = executorService.submit(()->{
                sleep(500); // This does NOT block an OS thread
                return "usingNewVirtualThreadPerTaskExecutor: "+Thread.currentThread();
            });
           return future.get();
        }
    }
}
