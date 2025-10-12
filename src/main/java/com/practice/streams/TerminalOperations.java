package com.practice.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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
