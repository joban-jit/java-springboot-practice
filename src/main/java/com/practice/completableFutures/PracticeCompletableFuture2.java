package com.practice.completableFutures;


import java.util.Random;
import java.util.concurrent.*;

import static com.practice.util.Util.sleep;

public class PracticeCompletableFuture2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        applyToEitherImply();
        System.out.println(handleImpl());
        completeOnTimeoutImpl();
        acceptEitherImpl();
        cancelImpl();
        System.out.println(withCustomExecutor());
        whenCompleteImpl();
    }

    private static void applyToEitherImply(){
//        applyToEither
//        What: Waits for either of two futures to finish first, then applies a function to the winner’s result.
//        Where: Use when you have two ways to get a result, and you want to use whichever finishes first.
//        Parameters:
//           Another future (CompletionStage)
//           A function taking the first completed result
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(100);
            return "Task 1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Task 2");

        future1.applyToEither(future2, result-> "In applyToEither. "+result)
                .thenAccept(System.out::println);

    }

    private static String handleImpl() throws ExecutionException, InterruptedException {
//        handle
//        What: Runs code when a future finishes—whether it completed successfully or with an exception.
//        Where: Use to handle both normal and error outcomes with a single function.
//        Parameters: A function that takes two arguments: the result (or null on error) and exception (or null on success)
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            if (new Random().nextBoolean()) {
                throw new RuntimeException("throwing error manually");
            }
            return "Executed without errors";
        }).handle((result, ex) -> {
            if (ex != null) {
                return "Recovering from exception";
            } else {
                return "Adding more info to result.";
            }
        }).thenApply(result -> "In handleImpl " + result);
        return future.get();
    }

    private static void completeOnTimeoutImpl(){
//        completeOnTimeout
//        What: If a CompletableFuture doesn’t finish within a certain time, it completes with a default value instead.
//        Where: Use when you want a result no matter what, even if the async task is slow or fails to finish in time.
//        Parameters:
//          Default value to use (of type T)
//          Amount of time to wait (long)
//          Time unit (e.g., TimeUnit.SECONDS)
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                    sleep(2000);
                    return "Original task completed";
                }).completeOnTimeout("Default message as original is not completed", 1, TimeUnit.SECONDS)
                .thenAccept(result -> System.out.println("In completeOnTimeoutImpl " + result));
        future.join();
    }



    private static void acceptEitherImpl(){
//        acceptEither
//        What: Waits for either of two futures to finish first, then consumes the result (does not return a value).
//        Where: Use when you just want to do something with the first result you get (like print or update UI).
//        Parameters:
//              Another future (CompletionStage)
//              A consumer (a function taking the result and returning void)

//        Difference from anyOf():
//        anyOf() returns a new CompletableFuture<Object>.
//        acceptEither() executes a consumer immediately when any one completes.
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Task 1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "Task 2";
        });

        CompletableFuture<Void> voidFuture = future1.acceptEither(future2, result -> System.out.println("In acceptEitherImpl, " + result));
        voidFuture.join();
    }

    private static void whenCompleteImpl(){
//        whenComplete
//        What: Runs code after the future is finished, whether it was successful or failed (but does not affect the result).
//        Where: Use to perform actions after an async task, like logging or cleanup.
//        Parameters:
//              A function that takes the result and the exception (if any).
        // Use handle() to recover from errors using Default value and transform the result
        // Use whenComplete you are want to print/log after finishes whether success or error
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (new Random().nextBoolean()) {
                throw new RuntimeException("Manually throwing error");
            }
            return "Original task completed";
        });
        future.whenComplete((result, ex)->{
            if(ex!=null){
                System.out.println("In whenCompleteImpl, Exception happened "+ex.getMessage());
            }else {
                System.out.println("whenCompleteImpl,"+result);
            }
        }).join();
    }

    private static void cancelImpl(){
//        cancel
//        What: Cancels the CompletableFuture if it isn’t already done, stopping the async task.
//        Where: Use when you want to stop the operation before it finishes.
//        Parameters:
//              A boolean (mayInterruptIfRunning) to say whether to interrupt if the task is running.
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "In cancelImpl: Task completed";
        });
        sleep(1000);
        boolean cancel = future.cancel(true);
        System.out.println("Cancelled? "+cancel);
        try
        {
            System.out.println(future.join());
        } catch (Exception e) {
            System.out.println("In cancelImpl, Exception after cancel: "+e);
        }
    }

    private static String withCustomExecutor(){
        try(ExecutorService executorService = Executors.newFixedThreadPool(4)){
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello", executorService)
                    .thenApplyAsync(res -> "In withCustomExecutor " + res, executorService)
                    .thenCombine(CompletableFuture.supplyAsync(() -> "World", executorService), (res1, res2) -> res1 +" "+ res2);

            return future.join();
        }

    }

}
