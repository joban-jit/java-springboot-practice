package com.practice.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TerminalOperations {

    // Reductions are a special type of terminal operation where ALL the
    // contents of the stream are combined into a single primitive or Object(e.g long or Collection)
    // e.g. count() ===> returns long,
    // min(), max() ===> returns Optional<T> - stream may be empty
    // why empty because you might have filtered all the data before it comes to min() or max()
    // reduce(), collect() ===> returns "it varies"

    // below may appear as reduction, but they aren't as these below operation may not look at all the elements
    // findAny(), findFirst() ===> returns Optional<T>
    // allMatch(), anyMatch(), noneMatch() ===> returns boolean

    public static void main(String[] args) {

//        commonTerminalOperations();
        reduceTerminalOperation();



    }

    private static void reduceTerminalOperation(){
        // the reduce() method combines a stream into a single object.
        // It is a reduction, which means it processes all elements.
        // most common way of doing a reduction is to start with an initial value
        // and keep merging it with the next value.

        // T reduce(T identity, BinaryOperator<T> accumulator)
        // and BinaryOperator<T> has this method T apply(T,T); accept and return same type
        // The "identity": is initial value of the reduction and also what is returned if stream is empty.
        // This means that there will always be a result and thus Optional is not the return type
        // (on this version of reduce())
        // The "accumulator" combines the current result with the current value in the stream.

        String name = Stream.of("s", "e","a","n")
//                .filter(s->s.length()>2) // if filter out then first reduce -> "nothing" and 2nd reduce=> ""(empty str)
//                .reduce("nothing", (s,c)->s+c); // nothings, nothingse, nothingsea, nothingsean
                .reduce("", (s,c)->s+c); // s, se, sea, sean
        System.out.println(name); // sean

        Integer product = Stream.of(2, 3, 4)
                .reduce(1, (a, b) -> a * b);
        System.out.println(product);
        Integer totalSum = Stream.of(2, 3, 4).reduce(0, (a, b) -> a + b);
        System.out.println(totalSum);
        int n = 5;
        int factorial = IntStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
        System.out.println(factorial);

        // Optional<T> reduce(BinaryOperator<T> accumulator)
        // when you leave out the identity, an Optional is returned because there may not be any data
        // (all the elements could have been filtered out earlier).
        // There are 3 possible results:
        //          a) empty stream => empty Optional returned
        //          b) one element in stream => that element is returned
        //          c) multiple elements in stream => accumulator is applied

        BinaryOperator<Integer> op = (a,b)-> a+b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(6);
        Stream<Integer> multipleElements = Stream.of(3,4,5);
        empty.reduce(op).ifPresent(System.out::println); // no output
        oneElement.reduce(op).ifPresent(System.out::println); // 6
        multipleElements.reduce(op).ifPresent(System.out::println); // 12

        // why not just require the identity and remove this method?
        // Sometimes it is nice to know if the stream is empty as opposed to the case where there is value
        // returned from the accumulator that happens to match the identity (however unlikely).

        Integer val = Stream.of(1, 1, 1)
//                .filter(i->i>5) //
                .reduce(1, (a, b) -> a); // would return 1
        System.out.println(val);
        // in above example: we will get return value as 1 , if we use the filter (stream is filtered out so it is empty) then it is identity , if we don't use
        // filter then it is accumulator. so we are getting same result for both 1. stream is empty and 2. stream is not empty
        // so this is reason we have two versions of reduce with and without identity

        // <U> U reduce(U identity, BiFunction accumulator, BinaryOperator combiner)
        // we use this version when we are dealing with different types, allowing us to create "intermediate reductions"
        // and then combine them at the end.
        // This is useful when working with parallel streams - the streams can be decomposed and reassembled
        // by separate threads.
        // For example, if wanted to count the length of four 1000-character strings, the first 2 values and last 2 values
        // could be calculated independently. The intermediate results(2000) would be then be combined into a final value
        // (4000).

        // example: we want to count the number of characters in each string
        Stream<String> stream = Stream.of("car", "bus", "train", "airplane");

               int length = stream.parallel()
                       .reduce(0,
                               (n0, str) -> n0 + str.length(), // n0 is Integer
                               (n1,n2)-> n1+n2 // n1 and n2 are results BiFunction and both are integer
                       );
        System.out.println(length);

        // another way to write it
        int sum = Stream.of("car", "bus", "train", "airplane")
                .mapToInt(String::length)
                .sum();
        System.out.println(sum);


    }

    private static void commonTerminalOperations() {
        // count
        long count = Stream.of("dog", "cat")
                .count();
        System.out.println(count);

        // Optional<T> min(Comparator)
        // min and max takes comparator as input as they need to compare values based on something
        Optional<String> minLength = Stream.of("deer", "horse", "pig")
                .min(Comparator.comparingInt(String::length));
        minLength.ifPresent(System.out::println);
        // Optional<T> max(Comparator)
        Comparator<Integer> maxValueComparator = Integer::compare;
        Optional<Integer> maxValue = Stream.of(4,6,2,12,9)
                .max(maxValueComparator);
        maxValue.ifPresent(System.out::println);

        // findAny or FindFirst are terminal operations but not reductions as they sometimes return without
        // processing all the elements in the stream. Reductions reduce the entire stream into one value
        //        Optional<T> findAny()
        Optional<String> any = Stream.of("John", "Paul").findAny();
        any.ifPresent(System.out::println);// John (Usually)
        //        Optional<T> findFirst()
        Optional<String> first = Stream.of("John", "Paul").findFirst();
        first.ifPresent(System.out::println);

        // anyMatch, allMatch, noneMatch
        List<String> names = Arrays.asList("Alan", "Brian", "Colin");
        Predicate<String> pred = name-> name.startsWith("A");
        System.out.println(names.stream().anyMatch(pred)); // true (one does)
        System.out.println(names.stream().allMatch(pred)); // false ( not all match, two don't)
        System.out.println(names.stream().noneMatch(pred)); // false (one does)

        // Notes: forEach is also a method in the Collection interface
        // Streams cannot be source of for-each loop
        // because streams do not implement the Iterable interface
        // e.g. Stream<Integer> s = Stream.of(1);
        // for(Integer i: s){} // error: required array or Iterable
    }

}
