package com.practice.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class IntermediateOperationsPractice {
    public static void main(String[] args) {
        List<CrewMember> listOfCrewMembers = getCrewMembers();


        // 1. Filter employees by salary range >=5000
        listOfCrewMembers.stream()
                .filter(crewMember -> crewMember.salary()>=5000)
                .forEach(System.out::println);

        // 2. Sort employees by age and then by name
        List<CrewMember> sortedByAgeAndName = listOfCrewMembers.stream()
                .sorted(
                        Comparator.comparingInt(CrewMember::age)
                                .thenComparing(Comparator.comparing(CrewMember::name))
                )
                .toList();
//        sortedByAgeAndName.forEach(System.out::println);

        // 3. Find employee with maximum salary
        Optional<CrewMember> maxSalary = listOfCrewMembers.stream()
                .max(Comparator.comparingDouble(CrewMember::salary));
        System.out.println(maxSalary.get());

        // 4. Print all distinct departments:
        listOfCrewMembers.stream()
                .map(CrewMember::department)
                .distinct()
                .forEach(System.out::println);

        // 5. Calculate total salaries of employees
        double sum = listOfCrewMembers.stream()
                .mapToDouble(CrewMember::salary)
                .sum();
        System.out.println(sum);

    }

    private static List<CrewMember> getCrewMembers() {
        List<CrewMember> listOfCrewMembers = new ArrayList<>();

        listOfCrewMembers.add(new CrewMember("Ram", 30, "Engineering", 5000, "SDE III", 10));
        listOfCrewMembers.add(new CrewMember("Shyam", 28, "Engineering", 1000, "SDE II", 8));
        listOfCrewMembers.add(new CrewMember("Joe", 25, "Sales", 550.56, "Sales Associate", 5));
        listOfCrewMembers.add(new CrewMember("Rohit", 40, "Management", 10000, "Senior Manager", 19));
        listOfCrewMembers.add(new CrewMember("James", 40, "Management", 10001, "Senior Manager", 19));
        return listOfCrewMembers;
    }
}

record CrewMember(
         String name,
         int age,
         String department,
         double salary,
         String designation,
         int yearsOfExperience
){

}
