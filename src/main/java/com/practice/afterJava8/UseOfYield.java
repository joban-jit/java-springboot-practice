package com.practice.afterJava8;

public class UseOfYield {
    /*
    You need yield when a case has a block { ... } and you want to return a value from that block.
     Because blocks cannot automatically return a value
     */
    public static void main(String[] args) {
        String day = "WEDNESDAY";
        System.out.println(getDayType(day));
        //It's a weekday
        //Weekday
        System.out.println(getDayTypeOldWay(day));
        //It's a weekday
        //Weekday
        day = "SUNDAY";
        System.out.println(getDayType(day));
        //It's a weekend
        //Weekend
        System.out.println(getDayTypeOldWay(day));
        //It's a weekend
        //Weekend
        day = "AFDSL";
        System.out.println(getDayType(day));
        //Unknown
        System.out.println(getDayTypeOldWay(day));
        //Unknown



        // below is showing we can't use return in block
//        int x = 1;
//        int result = switch (x) {
//            case 1 -> {
//                System.out.println("Doing work...");
//                return 999;     // not allowed
//            }
//            default -> {
//                yield 0;
//            }
//        };
    }

    private static String getDayType(String day) {
        String dayType = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> {
                System.out.println("It's a weekday");
                yield "Weekday";
            }
            case "SATURDAY", "SUNDAY" -> {
                System.out.println("It's a weekend");
                yield "Weekend";
            }
            default -> {
                yield "Unknown";
            }
        };
        return dayType;
    }


    private static String getDayTypeOldWay(String day) {
        switch (day) {
            case "MONDAY":
            case "TUESDAY":
            case "WEDNESDAY":
            case "THURSDAY":
            case "FRIDAY":
                System.out.println("It's a weekday");
                return "Weekday";
            case "SATURDAY":
            case "SUNDAY":
                System.out.println("It's a weekend");
                return "Weekend";

            default:
                return "Unknown";
        }
    }

}





