package com.practice.streams;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntermediateOperations {
    // Unlike terminal operation, an intermediate operation produces a "stream" as a result
    public static void main(String[] args) {
//        filterDistinctLimitOperations();
//        mapFlatMap();
        sorted();
//        mapMulti();


    }

    private static void sorted() {
        // sorted() returns a stream with the elements sorted
        // just like sorting arrays, java uses natural ordering(numbers-> ascending, string/chars -> alphanumeric order) unless we provide a comparator
        // sorted() is a stateful intermediate operation; it needs to see all the data before it can sort it
        Employee e1 = new Employee(1, "John");
        Employee e2 = new Employee(2, "Harry");
        Stream.of(e2,e1)
                .sorted(Comparator.comparingInt(p->p.id()))
                .forEach(System.out::println);
        //Employee[id=1, name=John]
        //Employee[id=2, name=Harry]
        Stream.of(e2,e1)
                .sorted(Comparator.comparing(p->p.name()))
                .forEach(System.out::println);
        //Employee[id=2, name=Harry]
        //Employee[id=1, name=John]

        Stream.of("Tim", "Jim", "Peter", "Ann", "Marry")
                .filter(name-> name.length()==3)
                .peek(name-> System.out.println("1. "+name)) // Tim, Jim, Ann
                .sorted() // Tim, Jim, Ann (sorted)
                .peek(name-> System.out.println("2. "+name)) // Ann, Jim
                .limit(2)
                .forEach(name-> System.out.println("3. "+name)); // Ann, Jim
        //1. Tim
        //1. Jim
        //1. Ann
        //2. Ann
        //3. Ann
        //2. Jim
        //3. Jim
    }

    private static void mapFlatMap() {

        // map() creates a one to one mapping between elements in the stream and elements in the next stage of the stream
        // map() is for transforming data
        Stream.of("book", "pen", "ruler")
                .map(s -> s.length())
                .forEach(System.out::println); // 4 3 5
        // here we got Stream<String> and using map we converted into Stream<Integer>

        //mapToInt returns IntStream
        // perfect for numeric operations like sum(), average()..etc
        // we can do the same using mapToInt, but it returns IntStream and not Stream<Integer>
        //advantages: Emits primitive ints directly — no boxing/unboxing overhead.
        Stream.of("book", "pen", "ruler")
                .mapToInt(s -> s.length())
                .forEach(System.out::println); // 4 3 5

        //mapToDouble returns DoubleStream
        // perfect for numeric operations like sum(), average()..etc
        List<Integer> prices = List.of(10, 20);
        prices.stream()
                .mapToDouble(p -> p * 1.18)
                .forEach(System.out::println);
        //11.799999999999999
        //23.599999999999998

        //mapToLong returns LongStream
        // perfect for numeric operations like sum(), average()..etc
        List<String> nums = List.of("100", "200");

        nums.stream()
                .mapToLong(Long::parseLong)
                .forEach(System.out::println);
        //100
        //200

        // mapToObj converts a primitive stream (IntStream, DoubleStream, LongStream) back to object stream Stream<R>
        //int to String obj
        IntStream.range(1, 3) // 1,2
                .mapToObj(i -> "Number " + i)
                .forEach(System.out::println);
        //Number 1
        //Number 2

        // another example
        IntStream.range(1, 4) // 1, 2, 3
                .mapToObj(i -> new Employee(i, "Emp" + i))
                .forEach(System.out::println);
        //Employee[id=1, name=Emp1]
        //Employee[id=2, name=Emp2]
        //Employee[id=3, name=Emp3]

        // flatMap() takes each element in the stream
        // flattens nested streams (or collections of collections).
        //It takes each element that produces a stream, and merges (“flattens”) all those streams into one continuous stream.
        // e.g. Stream<List<String>> and makes any elements it contains top-level elements in a single stream e.g. Stream<String>

        List<String> l1 = List.of("sean", "desmond");
        List<String> l2 = List.of("mary", "ann");
        Stream<List<String>> streamOfLists = Stream.of(l1, l2);

        streamOfLists
                //.map(List::stream) // gives Stream<Stream<String>>
                .flatMap(list -> list.stream()) // gives Stream<String>
                .forEach(System.out::println);
        //sean
        //desmond
        //mary
        //ann

        // why call stream() method here in flatMap
        // flatMap() expects your mapping function to return a Stream for each element,
        // so your function must return a Stream<R> - not a list , not an array just Stream
        // so if your element contains a collection (like a List,Set or array)
        // you usually turn that collection into a Stream - by calling .stream()

        // Special case: you only skip .stream() when your mapping function already returns a Stream.
        // e.g // no .stream() needed here in below example: each element (s1, s2) is already a Stream<String>
        Stream<String> s1 = Stream.of("A", "B");
        Stream<String> s2 = Stream.of("C", "D");

        Stream.of(s1, s2)
                .flatMap(i -> i)
                .forEach(System.out::println);
        //A
        //B
        //C
        //D

        // for array, you use : Arrays.stream(array)
        // for primitive array, you use: Arrays.stream(intArray)

        // e.g.
        List<String[]> data = List.of(
                new String[]{"A", "B"},
                new String[]{"C", "D"},
                new String[]{"E"}
        );
        // data looks like this [ ["A", "B"], ["C", "D"], ["E"] ]
        data.stream()
                .flatMap(Arrays::stream)
                .forEach(System.out::println);

        //A
        //B
        //C
        //D
        //E


        // Similarly we have flatMapToInt(), flatMapToDouble(), flatMapToLong() which returns primitive streams IntStream,
        // DoubleStream, LongStream respectively

        // using flatMap to return multiple elements as output for each element (before java 16, use mapMulti after Java16)
        Stream.of(1,2,3)
                .flatMap(n->Stream.of(n,n*10))
                .forEach(System.out::println);
        //1
        //10
        //2
        //20
        //3
        //30
        // works, but reason to use mapMulti for this scenario because
        // Each element creates a new temporary stream (Stream.of(...)).
        // so slightly inefficient and less direct


    }

    private static void mapMulti() {
        // mapMulti - a more efficient version of flatMap that avoids creating temporary streams to produce multiple outputs
        // for each element
        // you use a callback (BiConsumer) to "push" multiple results for each element
        // Each input produces two result
        //<R> Stream<R> mapMulti(BiConsumer<? super T, ? super Consumer<R>> mapper)
        Stream.of(1, 2, 3)
                .mapMulti(
                        (num, downstream) -> {
                            downstream.accept(num);
                            downstream.accept(num * 10);
                        }
                ).forEach(System.out::println);
        //1
        //10
        //2
        //20
        //3
        //30

        Stream.of("Bob", "Alice", "Tom", "Charlie")
                .mapMulti((name, down) -> {
                    if (name.length() > 3) {
                        down.accept(name);
                        down.accept(String.valueOf(name.length()));
                    }
                })
                .forEach(System.out::println);
        //Alice
        //5
        //Charlie
        //7
        // similarly we have mapMultiToInt(), mapMultiToLong(), mapMultiToDouble()
        //Emits primitive ints directly — no boxing/unboxing overhead.
    }

    private static void filterDistinctLimitOperations() {
        // filter - takes predicate as input
        Stream.of("galway", "mayo", "roscommon")
                .filter(countryName -> countryName.length() > 5)
                .forEach(System.out::println); //galway roscommon

        // distinct() returns stream with duplicate values removed +> it uses equals() (and hashCode()) i.e. case sensitive

        // distinct() is a stateful intermediate operation
        // it behaves like a filter - if it has not seen the object previously, it passes it on remembers it
        // if it has seen it already, it filters it out
        Stream.of("eagle", "eagle", "EAGLE")
                .peek(s -> System.out.println("1. " + s))
                .distinct()
                .forEach(s -> System.out.println("2. " + s));
        //1. eagle
        //2. eagle
        //1. eagle
        //1. EAGLE
        //2. EAGLE

        Employee e1 = new Employee(1, "John");
        Employee e2 = new Employee(1, "John");
        Employee e3 = new Employee(2, "Harry");
        Stream.of(e1, e2, e3)
                .peek(e -> System.out.println("1." + e))
                .distinct()
                .forEach(e -> System.out.println("2." + e));
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
        Stream.of(11, 22, 33, 44, 55, 66, 77, 88, 99)
                .peek(n -> System.out.println("A - " + n))
                .filter(n -> n > 40)
                .peek(n -> System.out.println("B - " + n))
                .limit(2)
                .forEach(n -> System.out.println("C - " + n));
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

record Employee(int id, String name) {
}
