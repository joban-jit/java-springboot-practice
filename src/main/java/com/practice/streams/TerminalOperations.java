package com.practice.streams;

import java.awt.image.ImageProducer;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
//        reduceTerminalOperation();
//        collectWithManualControlTerminalOperation();
//        collectApiDefinedCollectorsTerminalOperation();
        collectCollectorsGroupingByTerminalOperation();

    }

    private static void collectCollectorsGroupingByTerminalOperation() {
        // groupingBy() tells collect() to group all of the elements into a Map
        // it takes a Function which determines the keys in the Map
        // Each value is a List of all entries that match that key, The List is a default value but can be changed e.g. to Set

        Map<Integer, List<String>> groupByLength = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter")
                .collect(
                        // Passing in a Function that determines the key in the Map
                        Collectors.groupingBy(String::length)
                );
        System.out.println(groupByLength); //{3=[Joe, Tom, Tom], 4=[Alan], 5=[Peter]}

        // in above, we have duplicates Tom and Tom as value of those keys in Map is List
        // so we can override that to use Set
        // How: by passing a "downstream collector"- This is a collector that does something special with values
        // in our case it is telling collectors to use set
        Map<Integer, Set<String>> groupByLengthWithoutDuplicates = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter")
                .collect(
                        Collectors.groupingBy(
                                s->s.length(), // key Function
                                Collectors.toSet() // what to do with the values
                        )
                );
        System.out.println(groupByLengthWithoutDuplicates); //{3=[Joe, Tom], 4=[Alan], 5=[Peter]}

        // There is no guarantees on the type of Map returned
        // what if we wanted to ensure we got back a TreeMap but leave the values as List?
        // we can achieve this by using the "map type supplier" while passing down the toList collector.
         TreeMap<Integer, List<String>> treeMapWithGroupingBy = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter")
                .collect(
                        Collectors.groupingBy(
                                s->s.length(),
                                ()->new TreeMap<>(), // Supplier: which map type , in our case TreeMap
                                Collectors.toList() // downstream collector
                        )
                );
        System.out.println(treeMapWithGroupingBy); //{3=[Joe, Tom, Tom], 4=[Alan], 5=[Peter]}
        System.out.println(treeMapWithGroupingBy.getClass()); //class java.util.TreeMap


    }

    private static void collectApiDefinedCollectorsTerminalOperation() {
        // version of collect()- the one that accepts pre-defined collectors from API
        // we access these collectors via static methods on the Collectors interface

        String s0 = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.joining(", "));
        System.out.println(s0); //cake, biscuits, apple tart

        Double avg = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.averagingInt(s -> s.length()));
        // averagingInt(ToIntFunction) => int applyAsInt(T value)
        System.out.println(avg); //7.333333333333333

        // Collecting into Maps: two functions required
        // first function: tells collector how to create key
        // second function: tells collector how to create the value
        Map<String, Integer> map = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.toMap(
                        Function.identity(), // function for key === s->s
                        String::length)); // function for value === s-> s.length()
        System.out.println(map);

        // we want a map: number of characters in dessert name as key, dessert name as value
        // now we have this stream of "cake", "biscuits", "tart"
        // where cake and tart has same length so , in java we can't have duplicate keys
        // this leads to an exception as java doesn't know what to do ...
        // IllegalStateException: duplicate key 4
        // To get around this, we can supply a merge function, whereby we append the colliding keys values together
//        mergeFunction – a merge function, used to resolve collisions between values associated with the same key
        Map<Integer, String> mapWithAppend = Stream.of("cake", "biscuits", "tart")
                .collect(
                        Collectors.toMap(
                                s -> s.length(), // key is the length
                                s -> s, // value is the string
                                (s1, s2) -> s1 + ", " + s2 // (BinaryOperator)Merge function - what to do if we have duplicate keys
                                // -- append the values.
                        )
                );
        System.out.println(mapWithAppend); //{4=cake, tart, 8=biscuits}
        System.out.println(mapWithAppend.getClass()); //class java.util.HashMap

        // the maps returned are HashMaps but this is not guaranteed. What if we wanted a TreeMap implementation
        // so our keys would be sorted. The last argument caters for this

        TreeMap<String, Integer> treeMapOfDesserts = Stream.of("cake", "biscuits", "apple tart", "cake")
                .collect(
                        Collectors.toMap(
                                s -> s,
                                s -> s.length(),
                                (len1, len2) -> len1 + len2, // merge function as we have duplicate values in keys "cake" so we need to keep
                                // one key and adjust the value using this function: so we are adding the lengths
                                () -> new TreeMap<>() // mapFactory – a supplier providing a new empty Map into which the results will be inserted
                        )
                );
        System.out.println(treeMapOfDesserts); //{apple tart=10, biscuits=8, cake=8}
        System.out.println(treeMapOfDesserts.getClass()); //class java.util.TreeMap

    }

    private static void collectWithManualControlTerminalOperation(){

        // This is special type of reduction called a mutable reduction because we use same mutable object while
        // accumulating. This makes it more efficient than regular reductions.
        // Common mutable objects are StringBuilder and ArrayList
        // It is really useful method as it lets us get data out of streams and into other forms
        // e.g. Map's, List's and Set's

        // StringBuilder collect(Supplier<StringBuilder> supplier,
        //                  BiConsumer<StringBuilder, String> accumulator,
        //        BiConsumer<StringBuilder, StringBuilder> combiner)
        // this is version is used when you want complete control over how collecting should work.
        // the accumulator adds and element to the collections. e.g. the next String to the StringBuilder.
        // combiner takes two collections and merges them. It is useful in parallel processing

        StringBuilder word = Stream.of("ad", "jud", "i", "cate").parallel()
                .collect(() -> new StringBuilder(), // StringBuilder::new
                        (sb, str) -> sb.append(str), // StringBuilder::append
                        (sb1, sb2) -> sb1.append(sb2) // StringBuilder::append
                );
        System.out.println(word); //adjudicate
        // in accumulator
        // e.g. thread 1-> "adjud"
        // thread 2 returns "icate"
        // in combiner
        // "adjud" + "icate" combined into "adjudicate"
        // so this approach can be beneficial for parallel streams


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
        // this approach is mainly used with parallel stream, so "car" and "bus" can be taken by one thread and "train"
        // and "airplane" can be taken by another thread so result from thread 1 => 6 and result from thread 2 => 14,
        // and you can combine those together using combiner (BinaryOperator combiner) and get 19 as final result

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
