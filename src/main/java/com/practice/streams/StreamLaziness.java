package com.practice.streams;

import java.util.List;
import java.util.stream.Stream;

public class StreamLaziness {

    public static void main(String[] args) {

        steamLaziness();
    }



    private static void steamLaziness() {
        List<Double> temps = List.of(98.4, 100.2, 87.9, 102.8);
        long count = temps.stream()
                .peek(System.out::println)
                .filter(temp -> temp > 100)
                .peek(System.out::println)
                .count();
        System.out.println("Number of temps > 100 is "+count);
        /*
        // OUTPUT
        98.4
        100.2
        100.2
        87.9
        102.8
        102.8
        Number of temps > 100 is 2
         */

        Stream.of("Alex", "David", "April")
                .filter(s->{
                    System.out.println("filter: "+s);
                    return true;
                })
                .forEach(s-> System.out.println("forEach: "+s));
        /* Each element moves along the chain vertically
        // OUTPUT:
        filter: Alex
        forEach: Alex
        filter: David
        forEach: David
        filter: April
        forEach: April
         */

        /*
        this can help in reducing the actual number of operations - instead of mapping
        "Alex", "David", "April" and "Edward" and then anyMatch() on "Alex"
        (5 operations in total), we process the elements vertically resulting in
        only 2 operations. It shows the benefits to be had if we had millions of data
        elements to be processed.
         */
        Stream.of("Alex", "David", "April", "Edward")
                .map(s->{
                    System.out.println("map: "+s);
                    return s.toUpperCase();
                })
                .anyMatch(s->{// ends when first true is returned (Alex in our case)
                    System.out.println("anyMatch: "+s);
                    return s.startsWith("A");
                });
        // Output:
        // map: Alex
        // anyMatch:

        List<String> names = List.of("April", "Ben", "Charlie", "David", "Benildus", "Christian");
        names.stream()
                .peek(System.out::println)
                .filter(s->{
                    System.out.println("filter1: "+s);
                    return s.startsWith("B") || s.startsWith("C");
                })
                .filter(s->{
                    System.out.println("filter2: "+s);
                    return s.length()>3;
                })
                .limit(1) // intermediate operation which tracks state: in our case how many items
                .forEach(System.out::println);
        /*
        // OUTPUT

        April - peek
        filter1: April - filter1 removes April
        Ben - peek
        filter1: Ben - filter1 passes Ben on
        filter2: Ben - filter2 removes Ben
        Charlie - peek
        filter1: Charlie - filter1 passes Charlie on
        filter2: Charlie -  filter2 passes Charile on
        Charlie - forEach()
        NOTE: limit(1) means "David", "Benildus", "Christian" are not processed at all
         */
    }

}
