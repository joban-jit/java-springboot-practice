package com.practice.sealedClasses;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

public class PracticeSealedClasses {
    public static void main(String[] args) {
        Vehicle car = new Car();
        car.start();

        Shape shape = new Circle(5);
        printArea(shape);
        shape = new Rectangle(5,5);
        printArea(shape);
    }

    private static void printArea(Shape shape) {
        double area = switch (shape){
            case Circle c->c.area();
            case Rectangle r -> r.area();
            case Square s -> s.area();
        };
        System.out.println("Area "+area);
    }
}

// abstract sealed class
abstract sealed class Shape permits Circle, Rectangle, Square{
    public abstract double area();
}

@RequiredArgsConstructor
final class Circle extends Shape{
    private final double radius;

    public double area(){
        return Math.PI*radius*radius;
    }
}

@RequiredArgsConstructor
final class Rectangle extends Shape {
    private final double width, height;
    @Override
    public double area() { return width * height; }
}

@RequiredArgsConstructor
final class Square extends Shape {
    private final double side;
    @Override
    public double area() { return side * side; }
}












// Sealed class
sealed class Vehicle permits Car, Bike{
    void start(){
        System.out.println("Vehicle started");
    }
}

final class Car extends Vehicle{

    @Override
    void start(){
        System.out.println("Car started");
    }

}

final class Bike extends Vehicle{
    @Override
    void start(){
        System.out.println("Bike started");
    }
}


