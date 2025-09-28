package com.practice.lambdaExpressions;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

interface Evaluate<T extends Number> {
    boolean isNegative(T t);
}

public class APIFunctionalInterfaces {
    public static void main(String[] args) throws InterruptedException {
//        customFnInterface();
//        predicatePractice();
//        biPredicatePractice();
//        passingLambdaFnAsParam();
//        supplierPractice();
//        consumerPractice();
//        functionPractice();
//        unaryBinaryOperator();


    }

    private static void unaryBinaryOperator() {
        //  UnaryOperator<T> extends Function<T,T> interface
        // Type of input and output is same
        UnaryOperator<String> unaryOp = name->"My name is "+name;
        System.out.println("UnaryOperator: "+unaryOp.apply("Golu"));

        // BinaryOperator<T> extends BiFunction<T,T,T> interface
        // Type of inputs and output is same
//        BinaryOperator<String> binaryOp = (s1,s2)->s1.concat(s2);
        BinaryOperator<String> binaryOp = String::concat;
        System.out.println(binaryOp.apply("Golu ", "Molu"));
    }

    private static void functionPractice() {
        // function- R apply(T t) is abstract method
//        Function<String, Integer> fn2 = s->s.length();
        Function<String, Integer> fn2 = String::length;
        System.out.println(fn2.apply("Moscow"));

        // BiFunction - R apply(T t, U u) is abstract method it has
        BiFunction<String,String,Integer> biFunction = (s1, s2)->s1.length()+s2.length();
        System.out.println(biFunction.apply("Golu", "Molu"));

//        BiFunction<String,String,String> biFn2 = (s1,s2)->s1.concat(s2);
        BiFunction<String,String,String> biFn2 = String::concat;
        System.out.println(biFn2.apply("Golu ", "Molu"));
    }

    private static void consumerPractice(){
        // Consumer - void accept(T t)
//        Consumer<String> printC = s-> System.out.println(s);
        Consumer<String> printC = System.out::println;
        printC.accept("To be or not to be , that is the question");
        List<String> names = List.of("John", "Marry");
        names.forEach(printC);
        // BiConsumer - void accept(T t, U u)
        var mapCapitalCities = new HashMap<String, String>();
//        BiConsumer<String, String> biConsumerFn = (k,v)->mapCapitalCities.put(k,v);
        BiConsumer<String, String> biConsumerFn = mapCapitalCities::put;
        biConsumerFn.accept("Dublin", "Ireland");
        biConsumerFn.accept("New Delhi", "India");
        System.out.println(mapCapitalCities);

        BiConsumer<String, String> mapPrint = (k,v)-> System.out.println("Key: "+k+" is capital of: "+v);
        mapCapitalCities.forEach(mapPrint);


    }

    private static void supplierPractice() throws InterruptedException {
        // Supplier = T get() is abstract method
//        Supplier<StringBuilder> supSB = () -> new StringBuilder();
        System.out.println("Supplier..");
        Supplier<StringBuilder> supSB = StringBuilder::new;
        System.out.println("Supplier SB: " + supSB.get()
                .append("SK")
                .append(" SK1"));

        Supplier<LocalDateTime> supDateTime = LocalDateTime::now;
        System.out.println("Supplier datetime: "+supDateTime.get());
        Thread.sleep(2000);
        System.out.println("Supplier datetime: "+supDateTime.get()); // 2 secs after the first one

        Supplier<Double> sRandom = Math::random;
        System.out.println(sRandom.get());
    }

    private static void customFnInterface() {
        // Using custom functional interface
        System.out.println("Custom Fn interface..");
        Evaluate<Integer> lambdaFn = i -> i < 0;
        System.out.println(lambdaFn.isNegative(-1));
        System.out.println(lambdaFn.isNegative(0));
    }

    private static void passingLambdaFnAsParam() {
        System.out.println("Passing Predicate as method param..");
        // Passing Predicate as method parameter
        int x = 4;
        System.out.println("is " + x + " Even: " + check(x, n -> n % 2 == 0));
        System.out.println("is " + x + " Odd: " + check(x, n -> n % 2 != 0));
        String name = "Mr. Jones";
        System.out.println("Does " + name + " starts with Mr. ? " + check(name, s -> s.startsWith("Mr.")));
        System.out.println("Does " + name + " starts with Ms. ? " + check(name, s -> s.startsWith("Ms.")));
    }

    private static void biPredicatePractice() {
        // Using BiPredicate- boolean test(T t, U u) is abstract method
        System.out.println("BiPredicate..");
        BiPredicate<String, Integer> biPredicateFn = (s, i) -> s.length() == i;
        System.out.println(biPredicateFn.test("Vatican City", 8));
        System.out.println(biPredicateFn.test("Vatican City", 12));
    }

    private static void predicatePractice() {
        System.out.println("Predicate..");
        // Using predicate - boolean test(T t) is abstract method
        Predicate<Integer> predicateFn = i -> i < 0;
        System.out.println(predicateFn.test(-1));
        System.out.println(predicateFn.test(0));
    }

    public static <T> boolean check(T t, Predicate<T> predicateFn) {
        return predicateFn.test(t);
    }
}
