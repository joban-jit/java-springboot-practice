package com.practice.individualConcepts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);
}
 if return values is >0 (+ve) then o1>o2 -> o1 comes after o2
 if return values is <0 (-ve) then o1<o2 -> o1 comes before o2
 if return values is 0 (0) then o1==o2

 Why do we need it?
 you need different sorting rules for the same class
 you want clean, reusable sorting logic
 you can't modify a class (so you can't make it Comparable)
 */

public class ComparatorPractice {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(List.of(
                new Person("Alex", 30, 50000),
                new Person("Alex", 29, 65000),
                new Person("Bob", 25, 60000),
                new Person("Harry", 32, 70000),
                new Person("Charlie", 35, 55000)
        ));

//        basicComparator(people);
//        usingFactoryMethods(people);
//        chainingComparators(people);
//        reversingOrder(people);
            // more needed
    }



    private static void reversingOrder(List<Person> people) {
        Comparator<Person> byAgeReversed = Comparator.comparingInt(Person::age).reversed();
        people.sort(byAgeReversed);
        System.out.println(people);
        Comparator<Person> byNameThenOnlyAgeDesc = Comparator.comparing(Person::name)
                .thenComparing(Comparator.comparingInt(Person::age).reversed());
        people.sort(byNameThenOnlyAgeDesc);
        System.out.println(people);
        Comparator<Person> byNameThenAgeBothDesc = Comparator.comparing(Person::name)
                .thenComparingInt(Person::age).reversed();
        people.sort(byNameThenAgeBothDesc);
        System.out.println(people);
    }

    private static void chainingComparators(List<Person> people) {
        /*
        First sort by name
        If names are equal → sort by age
         */
        Comparator<Person> byNameThenAge = Comparator.comparing(Person::name)
                .thenComparingInt(Person::age);
        people.sort(byNameThenAge);
        System.out.println(people);
    }

    private static void usingFactoryMethods(List<Person> people) {
//        Comparator<Person> byName = (p1, p2)-> p1.name().compareTo(p2.name());
        // Comparator has static factory methods
        Comparator<Person> byNameL = Comparator.comparing(p-> p.name());
        Comparator<Person> byNameMR = Comparator.comparing(Person::name);
        people.sort(byNameMR);
        System.out.println(people);

//        Comparator<Person> byAge = (p1,p2)-> Integer.compare(p1.age(), p2.age());
        Comparator<Person> byAgeL = Comparator.comparingInt(p->p.age());
        Comparator<Person> byAgeMR = Comparator.comparingInt(Person::age);
        people.sort(byAgeMR);
        System.out.println(people);

//        Comparator<Person> bySalary = (p1, p2)-> Double.compare(p1.salary(), p2.salary());
        // works with normal Comparator.comparing() as well
        Comparator<Person> bySalary = Comparator.comparingDouble(Person::salary);
        people.sort(bySalary);
        System.out.println(people);
    }

    private static void basicComparator(List<Person> people) {
        Comparator<Person> byName = (p1, p2)-> p1.name().compareTo(p2.name());
        people.sort(byName);
        System.out.println(people);

        //Comparator<Person> byAge = (p1,p2)-> p1.age() - p2.age(); - avoid this
        // because Subtraction-based comparison can overflow
        // e.g p1.age() = -1 and p2.age() = 1 _>>> p1.age()-p2.age() = 2 (+ve)
        // saying p1.age() comes after p2.age() which is not true

        // also Integer.compare(a, b) returns -1, 0, or 1 by comparing without arithmetic subtraction
        Comparator<Person> byAge = (p1,p2)-> Integer.compare(p1.age(), p2.age());
        people.sort(byAge);
        System.out.println(people);

        Comparator<Person> bySalary = (p1, p2)-> Double.compare(p1.salary(), p2.salary());
        people.sort(bySalary);
        System.out.println(people);
    }
}

record Person(String name, int age, double salary){}