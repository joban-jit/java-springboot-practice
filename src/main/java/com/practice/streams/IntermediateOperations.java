package com.practice.streams;


import java.util.stream.Stream;

public class IntermediateOperations {
    // Unlike terminal operation, an intermediate operation produces a "stream" as a result
    public static void main(String[] args) {
//        filterDistinctLimitOperations();
        mapFlatMapSortedOperations();


    }

    private static void mapFlatMapSortedOperations() {

    }

    private static void filterDistinctLimitOperations() {
        // filter - takes predicate as input
        Stream.of("galway", "mayo", "roscommon")
                .filter(countryName-> countryName.length()>5)
                .forEach(System.out::println); //galway, roscommon

        // distinct() returns stream with duplicate values removed +> it uses equals() (and hashCode()) i.e. case sensitive

        // distinct() is a stateful intermediate operation
        // it behaves like a filter - if it has not seen the object previously, it passes it on remembers it
        // if it has seen it already, it filters it out
        Stream.of("eagle", "eagle", "EAGLE")
                .peek(s-> System.out.println("1. "+s))
                .distinct()
                .forEach(s-> System.out.println("2. "+s));
        //1. eagle
        //2. eagle
        //1. eagle
        //1. EAGLE
        //2. EAGLE

        Employee e1 = new Employee(1,"John");
        Employee e2 = new Employee(1,"John");
        Employee e3 = new Employee(2,"Harry");
        Stream.of(e1,e2,e3)
                .peek(e-> System.out.println("1."+e))
                .distinct()
                .forEach(e-> System.out.println("2."+e));
        //1.Employee[id=1, name=John]
        //2.Employee[id=1, name=John]
        //1.Employee[id=1, name=John]
        //1.Employee[id=2, name=Harry]
        //2.Employee[id=2, name=Harry]
        //The distinct() operation removes duplicates based on equals() and hashCode() of the stream elements.
        //Now, records in Java automatically generate equals() and hashCode() based on all components — here, both id and name.
        //So two Employee instances are considered equal if both their id and name are equal.

        // limit() is a short-circuiting stateful intermediate operation
        // Lazy evaluation - as not all elements are streamed only up to the limit
        Stream.of(11,22,33,44,55,66,77,88,99)
                .peek(n-> System.out.println("A - "+n))
                .filter(n->n>40)
                .peek(n-> System.out.println("B - "+n))
                .limit(2)
                .forEach(n-> System.out.println("C - "+n));
        // 11,22,33 - filtered out
        // 55,66 printed as part of last forEach operation because of limit is 2
        // 66,77,88,99 not streamed because limit already crossed.

        //A - 11
        //A - 22
        //A - 33
        //A - 44
        //B - 44
        //C - 44
        //A - 55
        //B - 55
        //C - 55
    }


}

record Employee(int id, String name){}
