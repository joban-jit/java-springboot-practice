package com.practice.concurrency.completableFutures;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.practice.util.Util.sleep;

public class PracticeCompletableFuture1 {
//    Use get() if you need to explicitly handle thread interruption or checked errors
//    Use join() for cleaner code when you don't want checked exception handling - useful in lambda expressions
    // Both returns value/result

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runAsyncTask();
        System.out.println(supplyAsyncToReturn());
        System.out.println(thenApplyImpl());
        thenAcceptImpl();
        thenRunImpl();
        System.out.println(thenCombineImpl());
        System.out.println(thenComposeImpl());
        allOfImpl();
        acceptEitherImpl();
        System.out.println(anyOfImpl());
        System.out.println(handleException());
        System.out.println(handleTimeouts());

    }

    private static void runAsyncTask() throws ExecutionException, InterruptedException {
//        runAsync
//        What: Runs a task asynchronously (in another thread) that returns no result (void).
//        Where: Use when you want to run some code asynchronously but don't need a result.
//        Parameters: Takes a Runnable (a task with no input and no output).
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("In runAsyncTask Running a runnable task in thread " + Thread.currentThread().getName()));
        future.get();// Wait for completion
    }

    private static String supplyAsyncToReturn() throws ExecutionException, InterruptedException {
//        supplyAsync
//        What: Runs a task asynchronously that returns a result.
//        Where: Use when you want to start an async computation that produces a value.
//        Parameters: Takes a Supplier<T> (a task that returns a value of type T).
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "In supplyAsyncToReturn Hello from " + Thread.currentThread().getName() + " using supplyAsync.");
        return future.get();
    }

    private static String thenApplyImpl() throws ExecutionException, InterruptedException {
//        thenApply
//        What: Transforms the result of a completed asynchronous computation.
//        Where: Use when you want to apply a function to the async result and return a new value.
//        Parameters: Takes a Function<T, R> (a function transforming input T to output R).
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "In thenApplyImpl")
                .thenApply(result -> result + " added thenApply")
                .thenApply(String::toUpperCase);
        return future.get();
    }

    private static void thenAcceptImpl() throws ExecutionException, InterruptedException {
//        thenAccept
//        What: Performs an action using the result of an async operation, but doesn’t return anything.
//        Where: Use when you want to consume the result (e.g., print it) without transforming it.
//        Parameters: Takes a Consumer<T> (a function that accepts a T and returns void
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "In thenAcceptImpl")
                .thenAccept(result -> System.out.println(result + " and using thenAccept"));
        future.get();
    }

    private static void thenRunImpl() throws ExecutionException, InterruptedException {
//        thenRun
//        What: Runs some code after an async task completes, but does not get the result.
//        Where: Use when you just want to trigger another action after a task finishes, regardless of its output.
//        Parameters: Takes a Runnable (no input, no output).
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "In thenRunImpl")
                .thenApply(result -> result + " in thenApply")
                .thenAccept(result -> System.out.println(result + " printing in thenAccept"))
                .thenRun(() -> System.out.println("Printing in thenRun"));
        future.get();
    }

    private static String thenCombineImpl() throws ExecutionException, InterruptedException {
//        thenCombine
//        What: Combines results of two async computations when both are done.
//        Where: Use when you have two independent async tasks and want to combine their results.
//        Parameters:
//            Another CompletionStage<U> (the second async task)
//            A BiFunction<T, U, R> (a function that takes both results and produces a combined result)
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "In thenCombineImpl, first task");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "now in second task");
        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + ", " + result2);
        return combinedFuture.get();
    }

    private static String thenComposeImpl() throws ExecutionException, InterruptedException {
//        thenCompose takes
//        What: Chains two async operations where the second depends on the result of the first.
//        Where: Use when an async task returns another async task, and you want to flatten them.
//        Parameters: Takes a Function<T, CompletionStage<U>>—a function returning another async.

        CompletableFuture<String> composedFuture = CompletableFuture.supplyAsync(() -> "In thenComposeImpl, firstTask")
                .thenCompose(result1 -> CompletableFuture.supplyAsync(() -> result1 + ", Now 2nd Task"));
        return composedFuture.get();
    }

    private static void allOfImpl() throws ExecutionException, InterruptedException {
//        allOf
//        What: Waits for all given CompletableFutures to complete.
//        Where: Use when you have multiple async tasks and want to continue only after all finish.
//        Parameters: Takes varargs (any number) of CompletableFuture<?> objects.
//        Returns: A new CompletableFuture<Void> that completes when all futures complete.
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "In allOfImpl Task 1");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "Task 2");
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "Task 3");

        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3);
        all.join(); // Wait for all
        System.out.println(f1.get() + ", " + f2.get() + ", " + f3.get());

    }

    private static String anyOfImpl() throws ExecutionException, InterruptedException{
//        anyOf
//        What: Waits for any one of the given CompletableFutures to complete.
//        Where: Use when you have multiple async tasks and want to proceed as soon as one completes.
//        Parameters: Takes varargs of CompletableFuture<?> objects.
//        Returns: A new CompletableFuture<Object> completing when the first future completes, with its result.
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Task 1");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "Task 2");
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "Task 3");
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(f2, f1, f3);
        CompletableFuture<String> future = anyOfFuture.thenApply(result -> "In anyOfImpl First task completed is " + result);
        anyOfFuture.join();// wait for result
        return future.get();
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


    private static String handleException() throws ExecutionException, InterruptedException{
//        exceptionally
//        What: Handles exceptions (errors) during async execution.
//        Where: Use when you want to provide fallback or recovery logic if the async computation fails.
//        Parameters: Takes Function<Throwable, T> which receives the exception and returns a fallback value of type T.
//        Returns: A new CompletableFuture with recovered result or original result if no exception.
        CompletableFuture<String> exceptionHandledFuture = CompletableFuture.supplyAsync(() -> {
            Random random = new Random();
            boolean randomBoolean = random.nextBoolean();
            if (randomBoolean) {
                throw new RuntimeException("Manually throwing exception");
            }
            return "In handleException, OK ";
        }).exceptionally(ex -> "In handleException Recovered from exception: " + ex.getMessage());
        return exceptionHandledFuture.get();
    }

    private static String handleTimeouts() throws ExecutionException, InterruptedException{
//        orTimeout
//        What: Automatically completes the CompletableFuture exceptionally if it does not finish in given time.
//        Where: Use to fail early if an async task is taking too long.
//        Parameters: Takes a long timeout value and a TimeUnit (e.g., seconds, millis).
//        Returns: The same CompletableFuture but with a timeout applied.
        CompletableFuture<String> handleTimeoutFuture = CompletableFuture.supplyAsync(() -> {
                    sleep(2000);
                    return "In handleTimeouts, no timeouts";
                }).orTimeout(1, TimeUnit.SECONDS)
                .exceptionally(ex -> "In handleTimeouts, Timeout happened, creating this string in exceptionally block");
        return handleTimeoutFuture.get();
    }
}
