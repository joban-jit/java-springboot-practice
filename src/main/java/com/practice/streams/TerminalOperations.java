package com.practice.streams;

import java.util.Optional;
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

        long count = Stream.of("dog", "cat")
                .count();
        System.out.println(count);

        Optional<String> min = Stream.of("deer", "horse", "pig")
                .min((s1, s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println);
    }

}
