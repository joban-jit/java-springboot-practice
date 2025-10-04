package com.practice.streams;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CreatingStreams {
    public static void main(String[] args) throws IOException {

//        arrayStream();
//        defaultStreamInCollection();
//        streamOf();
//        filesLines();
        infiniteStream();
    }

    private static void infiniteStream(){
        // 1. Infinite streams of random unordered numbers between 0..9 inclusive
        // using static method of Stream => Stream<T> generate(Supplier<T> s)
        Stream<Integer> infStream = Stream.generate(() -> (int) (Math.random() * 10));
        // keeps going until I kill it
//        infStream.forEach(System.out::println);

        // 2. Infinite streams of ordered numbers
        // using iterate<T seed, UnaryOperator<T> fn) => UnaryOperator is Function<T,T>
        // seed : first element
        Stream<Integer> iteratedInfStream = Stream.iterate(2, n -> n + 2);
        // keeps going until I kill it
//        iteratedInfStream.forEach(System.out::println);

        // Infinite streams can be turned into finite streams with operations such as limit(long)
        Stream.iterate(3,n->n+2)
                .limit(10) // limit is a short-circuiting stateful
                .forEach(System.out::println);
    }

    private static void filesLines() throws IOException {
        Path path = Paths.get(Path.of("src/main/resources/myFile.txt").toUri());
        try(Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        }
        //this is first line
        //this is second line
        //this is last line

        // another example:
        List<Cat> cats = loadCats("src/main/resources/Cats.txt");
        cats.forEach(System.out::println);

    }

    private static void streamOf(){
        // Stream.of() is static generically typed utility method that
        // accepts a varargs parameter and
        // returns an ordered stream of those values
        // static <T> Stream<T> of (T.. values)
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        System.out.println(integerStream.count());
        Stream<Character> characterStream = Stream.of('a', 'b', 'c');
        System.out.println(characterStream.count());
        // we have Dog as static class
        Stream<Dog> dogStream = Stream.of(new Dog());
        dogStream.forEach(Dog::bark);
    }

    private static void defaultStreamInCollection() {
        // The default Collection interface method stream() is used
        List<String> animalList = List.of("cat", "dog", "sheep");
        // using stream() which is default method in Collection interface
        Stream<String> streamAnimals = animalList.stream();
        System.out.println("Number of animals: "+streamAnimals.count());

        // stream() is a default method in the Collections interface and therefore
        // is inherited by all classes that implement Collection.
        // Map is NOT one of those i.e. Map is not a Collection.
        // To bridge between the two, we use the Map method entrySet() to return a Set view
        // of Map (Set is a collection)

        Map<String, Integer> namesToAges = Map.of("Mike", 22, "Mary", 24, "Alice", 31);
        Stream<Map.Entry<String, Integer>> entryStream = namesToAges
                .entrySet() // get a Set(Entry<String,Integer>)
                .stream();
        System.out.println(entryStream.count());
        entryStream
                .forEach(e-> System.out.println(e.getKey()+": "+e.getValue()));
    }

    private static void arrayStream() {
        // Arrays.stream() - can be used to stream an array
        Double[] numbers = {1.1, 2.2, 3.3};
        Stream<Double> stream1 = Arrays.stream(numbers);
        System.out.println("Number of elements: "+stream1.count());
        // Output: Number of elements: 3
    }

    static class Dog{
        void bark(){
            System.out.println("Woof!, Woof!");
        }
    }

    private static List<Cat> loadCats(String filename) throws IOException {
        List<Cat> cats = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(filename))){
           stream.forEach(line->{
               String[] catArray = line.split("/");
               cats.add(new Cat(catArray[0],catArray[1]));
           });
        }
        // Note: inside above lambda expression we are adding elements to Arraylist
        // while we can add elements to "cats" we can't change what 'cats' refers to
        // i.e. we can't say cats = new ArrayList<>();
        return cats;
    }
}
