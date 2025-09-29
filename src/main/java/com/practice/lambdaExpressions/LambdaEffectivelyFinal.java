package com.practice.lambdaExpressions;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class LambdaEffectivelyFinal {
    String name = "";

    public static void main(String[] args) {
        List<String> al = List.of("John");
        int x = 12; // this should be final or effectively final IF you are using this in
        // lambda function
        // Because
        // Lambda take a snapshot/picture of local variables;
        // these variables MUST NOT change.
        // When we declare Lambda fn we are not executing it we are just declaring it
        // and
        // because we can be using this lambda multiple places so this can give you
        // unexpected results if this operation of changing local variable is allowed.

        // x++; not allowed to change

        Predicate<String> predicateLambdaFn = s->{
//            x++; so this operation is not allowed as it is changing the variable
            new LambdaEffectivelyFinal().name= "Kennedy"; // instance/class variables are OK
            System.out.println("x == "+x);
            return s.isEmpty() && x%2==0;
        };
        filterData(al,predicateLambdaFn); // predicateLambdaFn views 'x' as 12
        System.out.println(al);
        new LambdaEffectivelyFinal().name="Sean";

        // if 'x' was allowed to change, then the method and the lambda would
        // have 2 different views of 'x';
//        x++; and we can't do this as well , because we already used this in lambda function declaration
        filterData(al,predicateLambdaFn); // predicateLambdaFn views 'x' as 12
        System.out.println(al);


    }

    public static void filterData(List<String> list, Predicate<String> predicateLambdaFn){
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            if(predicateLambdaFn.test(iterator.next())){
                iterator.remove();
            }
        }

        // DO NOT use below if you have created list using
        // List<String> list = List.of("a", "b", "c");   // Java 9+
        //List<String> list = Arrays.asList("a", "b", "c"); // backed by array, fixed-size
        // Means both produce collections that cannot be structurally modified.
//        list.removeIf(predicateLambdaFn);
    }
}
