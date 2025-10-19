package com.practice.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.*;

public class PrimitiveStreams {

    public static void main(String[] args) {


        // IntStream - for primitives types int
        // DoubleStream - for primitive types double and float
        // LongStream - for primitive type long
        // Other primitive types like short, byte and char → must be boxed or mapped to one of the above


//        creatingPrimitiveStream();
        primitiveStreamsAPIs();
        // Note on some extra functional interfaces



    }

    private static void primitiveStreamsAPIs() {
        // These primitives stream in addition to containing many of the stream methods, also contain specialized methods
        // for working with numeric data
        // e.g.
        // min() returns OptionalInt, OptionalLong, OptionalDouble depends on type of primitive stream
        // max() returns OptionalInt, OptionalLong, OptionalDouble depends on type of primitive stream
        // sum() returns int, long or double  depends on type of primitive stream
        // average() returns OptionalDouble depends on type of primitive stream
        // summaryStatistics() returns IntSummaryStatistics, LongSummaryStatistics or DoubleSummaryStatistics
        OptionalInt max = IntStream.of(10, 20, 30).max();
        max.ifPresent(System.out::println); //30
        OptionalDouble min = DoubleStream.of(10.0, 20.0, 30.0).min();
        // NoSuchElementException is thrown if no value present
        System.out.println(min.orElseThrow()); // 10.0
        OptionalDouble average = LongStream.of(10L, 20L, 30L).average();
        // if not present it will use some default value here
        average.ifPresentOrElse(System.out::println, ()->Double.valueOf(0)); //20.0

        // summaryStatistics()
        intStats(IntStream.of(5,10,15,20));
        //Min: 5
        //Max: 20
        //Average: 12.5
        //Sum: 50
        //Count: 4
        intStats(IntStream.empty());
        //Min: 2147483647
        //Max: -2147483648
        //Average: 0.0
        //Sum: 0
        //Count: 0

    }
    private static void intStats(IntStream numbers){
        // note: here min, max, average doesn't come as Optional but  primitive
        // summaryStatistics is terminal operation
        IntSummaryStatistics intStats = numbers.summaryStatistics();
        int min = intStats.getMin();
        System.out.println("Min: "+ min);
        int max = intStats.getMax();
        System.out.println("Max: "+ max);
        double average = intStats.getAverage();
        System.out.println("Average: "+ average);
        long sum = intStats.getSum();
        System.out.println("Sum: "+ sum);
        long count = intStats.getCount();
        System.out.println("Count: "+ count);

    }

    private static void creatingPrimitiveStream() {
        int[] ia = {1, 2, 3};
        double[] da = {1.1, 2.2, 3, 3};
        long[] la = {1L, 2L, 3L};
        char[] ca = {'a', 'b', 'c'};
        byte[] ba = {1, 2, 3};
        IntStream iStream1 = Arrays.stream(ia);
        DoubleStream dStream1 = Arrays.stream(da);
        LongStream lStream1 = Arrays.stream(la);
        IntStream cStream1 = new String(ca).chars(); // unique
        IntStream bStream1 = IntStream.range(0, ba.length).map(i -> ba[i]);
//        iStream1.forEach(System.out::println); // 1 2 3
//        dStream1.forEach(System.out::println); // 1.1 2.2 3, 3
//        lStream1.forEach(System.out::println); // 1 2 3
//        cStream1.forEach(System.out::println); // 97 98 99
//        bStream1.forEach(System.out::println); // 1 2 3

        IntStream iStream2 = IntStream.of(1, 2, 3);
        DoubleStream dStream2 = DoubleStream.of(1.1, 2.2, 3.3);
        LongStream lStream2 = LongStream.of(1L, 2L, 3L);
        System.out.println(iStream2.count()+ ", "+dStream2.count()+", "+lStream2.count()); //3, 3, 3

        // let's say we want to total sum of numbers in as stream
        // without IntStream and using Stream and reduce(identity, accumulator)
        Stream<Integer> numbers = Stream.of(1,2,3);
        System.out.println(numbers.reduce(0, (a,b)->a+b)); // 6

        // Using IntStream and sum method
        IntStream intStream = IntStream.of(1, 2, 3);
        System.out.println(intStream.sum()); //6

        // you can create primitive stream is by mapping from another stream type
        // using map(), mapToDouble(), mapToInt(), mapToLong()...we have a couple of overloaded
        // methods of these where it accepts different of types of streams, like Stream of Integer, Double or Long



    }


}
