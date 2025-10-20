package com.practice.optionals;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class PracticeOptional {
    // Think of an Optional as a box that may or may not be empty
    // In addition to Optional<T>, there are Optional's for the primitive types
    // OptionalDouble, OptionalInt and OptionalLong
    public static void main(String[] args) {
//        commonOptionalMethods();
//        doOptionalNull();
        doOptionalPrimitiveAverage();

    }

    private static void doOptionalPrimitiveAverage(){
        OptionalDouble optAvg = IntStream.rangeClosed(1, 10).average();

//        optAvg.ifPresent(System.out::println);
        optAvg.ifPresent(d-> System.out.println(d));// 5.5 (used lambda to show consumer function accepts on primitive double)
        System.out.println(optAvg.getAsDouble()); // 5.5
        System.out.println(optAvg.orElseGet(()->Double.NaN));//5.5
    }

    private static void doOptionalNull(){
        Optional<String> optSK = howToDealWithNull("SK");
        optSK.ifPresent(System.out::println); // SK
        Optional<String> optNull = howToDealWithNull(null);
        System.out.println(optNull.orElseGet(()->"Empty optional")); // Empty optional
    }

    private static Optional<String> howToDealWithNull(String param){
//        if(param==null){
//            return Optional.empty();
//        }else{
//            return Optional.of(param);
//        }
        // Above can be written as single line using ofNullable()
        Optional optReturn = Optional.ofNullable(param);
        return optReturn;
    }

    private static void commonOptionalMethods() {
        Optional<Double> optAvg = calcAverage(50, 60, 70);
        // if you do a get() and the Optional is empty you get: NoSuchElementException: No value present
        // boolean isPresent() protects us from that
        if(optAvg.isPresent()){
            System.out.println(optAvg.get()); // 60.0
        }
        // void ifPresent(Consumer c)
        optAvg.ifPresent(System.out::println); //60.0
        // T orElse(T t)
        System.out.println(optAvg.orElse(Double.NaN)); // 60.0
        Optional<Double> emptyOpt = calcAverage(); // will return an empty Optional
        System.out.println(emptyOpt.orElse(Double.NaN)); //NaN
        // T orElse(Supplier<T> s)
        System.out.println(emptyOpt.orElseGet(()->Double.NaN)); //NaN
    }

    // a long way to calculate average (just for showing Optional)
    public static Optional<Double> calcAverage(int... scores){
        if(scores.length==0){
            return Optional.empty();
        }
        int sum=0;
        for(int score: scores){
            sum+=score;
        }
        return Optional.of((double)sum/scores.length);
        // one preferred way to calc average with int[] array
//        Arrays.stream(scores).average();
    }
}
