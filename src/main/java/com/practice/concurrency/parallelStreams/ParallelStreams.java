package com.practice.concurrency.parallelStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreams {
    // Without parallel streams, streams have processed the data one element at a time
    // with Parallel Streams, streams can process elements in a stream concurrently i.e. at the same time
    // Java achieves this by splitting the stream up into sub-streams and then the pipeline operations are performed
    // on the sub-streams concurrently(each sub-stream has its own thread)

    public static void main(String[] args) {

        // Creating parallel stream
        // using Collection api
//        Stream<String> animalStream = List.of("sheep", "pigs", "horses").parallelStream();
        // using Stream api
//        Stream<String> animalStream1 = Stream.of("sheep", "pigs", "horses").parallel();


        int sum = Stream.of(10, 20, 30, 40, 50, 60)
                .parallel()
                .mapToInt(n -> n.intValue())
                .sum();
        System.out.println("Sum == "+sum);// 210
        // in this things would happen in parallel
        // e.g. Thread 1 will take sum of 10 20 30 = 60
        // thread 2 will take sum of 40 50 60 = 150
        // and then result is combined 60+150=210

        // NOTE: Be careful if order is important, as the order of thread completion will determine
        // the final result (not the order in the original collection).

        Arrays.asList("a", "b", "c").stream().forEach(System.out::println); // a b c
        Arrays.asList("a", "b", "c").parallelStream().forEach(System.out::println); // b c a (order is not guaranteed)


        List<Integer> numbers = IntStream.range(1, 10_000_000).boxed().collect(Collectors.toList());
        //Parallel streams work best when:
        //1. CPU-Intensive Operations
        //Each element takes a significant amount of computation time.
        //Tasks are independent (no shared mutable state).
        //Data size is large enough to justify thread overhead
        //2. Large Collections
        //The collection has thousands or millions of elements.
        //You’re running pure functional transformations (no side effects).
        //3. Stateless, Associative Operations
        //Has no dependency on the order (e.g., sum, max, min, average).
        //Is associative — the result doesn’t depend on the order of combination.
        //double sum = numbers.parallelStream().mapToDouble(Math::sin).sum();

        // Avoid when your stream involves:
        //1. I/O-Bound Tasks
        //Reading/writing files.
        //Network or database calls.
        //Logging or printing to console.
        //Threads just block waiting for I/O — no performance gain.
        //2. Small Data Sets
        //Parallel overhead (splitting, joining, thread scheduling) outweighs the benefit.
        //Example: Processing a list of 100 elements → sequential stream is faster.
        //3. Shared Mutable State
        //Avoid if your operation modifies shared data structures (e.g., lists, maps, counters).
        // DON'T DO THIS
        List<Integer> result = new ArrayList<>();
        numbers.parallelStream()
                .forEach(n -> result.add(n * 2)); // race condition risk
        // DO THIS INSTEAD
        List<Integer> result1 = numbers.parallelStream()
                .map(n -> n * 2)
                .collect(Collectors.toList());

        //4. Order-Dependent Operations
        //Avoid if order matters (e.g., reading lines sequentially, preserving order for output).
        //Parallel streams may reorder processing for efficiency.
        //5. Custom Thread Pool Needs
        //parallelStream() always uses the common ForkJoinPool, which:
        //Affects other parallel tasks globally.
        //Can’t easily be tuned per stream.
        //ForkJoinPool customPool = new ForkJoinPool(8);
        //customPool.submit(() -> myList.parallelStream().forEach(...)).join();

    }
}
