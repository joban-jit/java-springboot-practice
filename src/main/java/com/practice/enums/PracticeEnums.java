package com.practice.enums;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

public class PracticeEnums {
    public static void main(String[] args) {
        System.out.println(PaymentStatus.COMPLETED.getDescription());
        double sum = Operation.ADD.apply(5, 4);
        System.out.println(sum);

        System.out.println(Operation.describe(Operation.ADD));

        System.out.println(getStatusMessage(Status.IN_PROGRESS));
        System.out.println(ShapeType.fromShape(new Circle(3)).toString());
        List<String> statuses = Arrays.stream(Status.values())
                .map(statusEnum-> statusEnum.name())
                .toList();
//        statuses.forEach(System.out::println);
        Status aNew = Status.valueOf("NEW");
        System.out.println(aNew.name());
        Status status = Arrays.stream(Status.values())
                        .filter(s->s.name().equalsIgnoreCase("in_progress"))
                                .findFirst()
                                        .orElseThrow();
        System.out.println(status);

        // EnumMap
        EnumMap<Role, Set<String>> permissions = new EnumMap<>(Role.class);
        permissions.put(Role.ADMIN, Set.of("READ", "WRITE", "DELETE", "BAN"));
        permissions.put(Role.MODERATOR, Set.of("READ", "WRITE", "BAN"));
        permissions.put(Role.USER, Set.of("READ", "WRITE"));
        permissions.put(Role.GUEST, Set.of("READ"));

        // lookup is O(1) and very memory-efficient
        Role currentRole = Role.MODERATOR;
        System.out.println("Moderator permissions: "+permissions.get(currentRole));

        for (var entry: permissions.entrySet()){
            System.out.println(entry.getKey()+"-" +entry.getValue());

        }


    }

    public static String getStatusMessage(Status status){
        return switch (status){
            case NEW -> "New Order";
            case IN_PROGRESS -> "Processing";
            case COMPLETED -> "Done";
            case CANCELLED -> "Cancelled";
        };
    }
}
