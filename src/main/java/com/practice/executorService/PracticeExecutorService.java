package com.practice.executorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static com.practice.util.Util.sleep;


public class PracticeExecutorService {
    public static void main(String[] args) throws Exception {
        try (ExecutorService service = Executors.newFixedThreadPool(3)) {
//            submitRunnable(service);
//            submitCallable(service);
//            shutdownImpl(service);
//            shutdownNowImpl(service);
            awaitTerminationImpl(service);
//            invokeAllAndInvokeAny(service);
        }
//        delayedAndScheduleTask();

    }

    private static void submitRunnable(ExecutorService executorService) {
        for (int i = 1; i <= 4; i++) {
            int taskId = i;
            executorService.submit(() -> {
                sleep(1000);
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
            });
        }
    }

    private static void submitCallable(ExecutorService executorService) throws ExecutionException, InterruptedException {
//        submit() returns Future for result.
//        future.get() blocks until done.
        Future<Integer> future = executorService.submit(() -> {
            sleep(1000);
            return new Random().nextInt(100);
        });
        System.out.println("Doing other work");
        System.out.println("Result: " + future.get());
    }

    private static void invokeAllAndInvokeAny(ExecutorService service) throws Exception {
        Random random = new Random();
        List<Callable<String>> tasks = List.of(
                () -> {
                    sleep(900);
                    return "Task 1: " + random.nextInt(100);
                },
                () -> {
                    sleep(500);
                    return "Task 2: " + random.nextInt(100);
                },
                () -> {
                    sleep(1000);
                    return "Task 3: " + random.nextInt(100);
                }

        );

        // invokeAll waits for all, and it returns List<Future<T>>
        List<Future<String>> futures = service.invokeAll(tasks);
        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // invokeAny returns first completed and it returns T result directly
        String result = service.invokeAny(tasks);
        System.out.println(result);
    }

    private static void delayedAndScheduleTask() {
        try (ScheduledExecutorService service = Executors.newScheduledThreadPool(2)) {
            // Delayed task
            ScheduledFuture<Integer> scheduledFuture = service.schedule(() -> new Random().nextInt(100), 2, TimeUnit.SECONDS);
            System.out.println("delayed task: " + scheduledFuture.get());
            // Scheduled task
            ScheduledFuture<?> repeatingTask = service.scheduleAtFixedRate(() -> System.out.println("Repeating task"), 1, 3, TimeUnit.SECONDS);
//            repeatingTask.get(); // to run this scheduleAtFixedRate in main method in this app, you don't need it for springboot app
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void shutdownImpl(ExecutorService service) {
//        Stops accepting new tasks.
//        Waits for all submitted tasks to finish.
//        Does NOT kill running tasks.
        IntStream.range(1, 4).forEach(i -> service.submit(() -> {
            System.out.println("In shutdownImpl Task " + i + " running in " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException ex) {
                System.err.println("Task " + i + " interrupted!");
            }
        }));

        sleep(1000);// Allow some tasks to start

        System.out.println("Forcefully shutting down ... ");
        service.shutdown(); // Interrupts running tasks
        System.out.println("Is shutdown initiated? " + service.isShutdown());
        // This task will NOT be accepted
        try {
            service.submit(() -> System.out.println("New Task"));
        } catch (Exception e) {
            System.out.println("Cannot submit new task: " + e);
        }
        // Main thread will exit but tasks will still run
        System.out.println("Main thread exiting after shutdown() call...");

    }

    private static void shutdownNowImpl(ExecutorService service){
//        Attempts to stop all actively executing tasks.
//        Returns a list of tasks that were submitted but not started.
//        Interrupts running tasks (if they handle interruption).
        IntStream.range(1,6).forEach(i->service.submit(()->{
            System.out.println("In shutdownNowImpl Task "+i+" running in "+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("Task " + i + " interrupted!");
                return; // Exit the task here
            }
            System.out.println("Task " + i + " finished");
        }));

        sleep(1000);
        System.out.println("Forcefully shutting down ...");
        List<Runnable> pendingTasks = service.shutdownNow();
        System.out.println("Pending tasks count: " + pendingTasks.size());
        System.out.println("Is shutdown initiated? " + service.isShutdown());
    }

    private static void awaitTerminationImpl(ExecutorService service){
//        Blocks until:
//          All tasks finish OR
//          Timeout occurs OR
//          Current thread is interrupted.
        IntStream.range(1, 4).forEach(i -> service.submit(() -> {
            System.out.println("In awaitTerminationImpl Task " + i + " running in " + Thread.currentThread().getName());
            try {
                Thread.sleep(1100L*i);
            } catch (InterruptedException ex) {
                System.err.println("Task " + i + " interrupted!");
            }
        }));

        service.shutdown();
        System.out.println("Waiting for termination...");
        try {
            if(!service.awaitTermination(3, TimeUnit.SECONDS)){
                System.out.println("Timeout reached, forcing shutdown...");
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Got Interrupted exception: "+e.getMessage());
            service.shutdownNow();
        }
        System.out.println("All tasks completed or forcefully terminated");


    }


}
