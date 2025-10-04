package com.practice.lambdaExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class MethodReferences {
    /**
     * there are four different styles/types:
     * 1. Bound - instance known at compile-time
     * 2. Unbound - instance provided at runtime
     * 3. Static
     * 4. Constructor
     *
     * With Method references, "context" is key
     *  - functional interface being created is hugely important in
     *    determining context
     * Compiler turns your method references into lambdas in the background
     */


    public static void main(String[] args) {
        boundMethodReferences();
        unboundMethodReferences();
        staticMethodReferences();
        constructorMethodReference();
        methodRefAndContext();
    }

    public static void methodRefAndContext(){
        // Method reference is always based on context, what is on left side, what is input, output
        // in below examples all 3 places we have Person::howMany as method reference but based on context surrounding it's
        // execution changes, input and output changes

        // No person being passed in --> Supplier
        Supplier<Integer> lambda1 = ()-> Person.howMany();
        Supplier<Integer> mr1 = Person::howMany;
        System.out.println(lambda1.get());
        System.out.println(mr1.get());

        // One person to be passed in --> Function
        Function<Person, Integer> lambda2 = person -> Person.howMany(person);
        Function<Person,Integer> mr2 = Person::howMany;
        System.out.println(lambda2.apply(new Person()));
        System.out.println(mr2.apply(new Person()));

        // Two person to be passed in --> BiFunction
        BiFunction<Person,Person, Integer> lambda3 = (p1,p2)->Person.howMany(p1,p2);
        BiFunction<Person,Person, Integer> mr3 = Person::howMany;
        System.out.println(lambda3.apply(new Person(), new Person()));
        System.out.println(mr3.apply(new Person(), new Person()));
    }

    public static void constructorMethodReference(){
        // Supplier<T> => T get()
        // default constructor with no argument/parameter
        Supplier<StringBuilder> sbL = ()-> new StringBuilder();
        Supplier<StringBuilder> sbMR = StringBuilder::new;
        StringBuilder sb1 = sbL.get();
        sb1.append("lambda version");
        System.out.println(sb1);

        StringBuilder sb2 = sbMR.get();
        sb2.append("method reference version");
        System.out.println(sb2);

        // Function<T,R> => R apply(T)
        // constructor with some arguments/parameters
        Function<Integer, List<String>> alL = size->new ArrayList<>(size);
        Function<Integer, List<String>> alMR = ArrayList::new;

        List<String> ls1 = alL.apply(10);
        ls1.add("21");
        System.out.println(ls1);
        List<String> ls2 = alMR.apply(5);
        ls2.add("88");
        System.out.println(ls2);

    }

    public static void staticMethodReferences(){
        // Static method references are considered UNBOUND also.
        // example with Consumer<T> => void accept(T t)
        //static method sort in Collections class: Collections.sort(List)
        // here sort() method is only looking for List as argument so it won't use sort(List,Comparator)
        Consumer<List<Integer>> sortL = list-> Collections.sort(list);
        Consumer<List<Integer>> sortMR = Collections::sort;
        List<Integer> listOfNumbers = Arrays.asList(2,1,5,4,9);
        sortL.accept(listOfNumbers); // execution
        System.out.println(listOfNumbers);
        listOfNumbers = Arrays.asList(8,12,4,3,7);
        sortMR.accept(listOfNumbers);
        System.out.println(listOfNumbers);
    }

    public static void unboundMethodReferences(){
        // Function<T,R> => R apply(T)
        Function<String,String> upperL = s->s.toUpperCase();
        Function<String,String> upperML = String::toUpperCase;
        // This function is unbound, so you need to specify which instance to call it on
        System.out.println(upperL.apply("sean"));
        System.out.println(upperML.apply("sean"));

        BiFunction<String,String,String> concatL = (s1,s2)->s1.concat(s2);
        BiFunction<String,String,String> concatMR = String::concat;

        // in our example
        // 1st parameter ("Sean") is used for execution the instance method
        // 2nd parameter ("Kennedy") is used as argument
        // "Sean ".concat("Kennedy")

        System.out.println(concatMR.apply("Sean ", "Kennedy"));

    }

    public static void boundMethodReferences(){
        String name = "Mr. Joe Bloggs";
        // Supplier<T>  => T get()
        // name's values is already known at compile time
        // we are using name directly in lambda functions in Supplier and Predicate because it is a final/effectively final
        Supplier<String> lowerL = ()-> name.toLowerCase(); // lambda
        Supplier<String> lowerMR = name::toLowerCase; // method reference

        // No need to say which instance to call it on - the supplier is bound to name
        System.out.println(lowerL.get());
        System.out.println(lowerMR.get());

        // Predicate<T> => boolean test(T t)
        // Even though startsWith is overloaded ( we have boolean startsWith(String) and boolean startsWith(String, int))
        // so we have two methods named startsWith
        // because we are creating a Predicate which has a functional method of test(T t),
        // so startsWith(String) will be used (as this is only one have only one parameter and it is of String type)
        // this is where "context" is important
        Predicate<String> titleL = (title)-> name.startsWith(title);
        Predicate<String> titleMR = name::startsWith;
        System.out.println(titleL.test("Mr."));
        System.out.println(titleMR.test("Ms."));
    }

}

class Person{
    public static Integer howMany(Person... people){
        return people.length;
    }
}
