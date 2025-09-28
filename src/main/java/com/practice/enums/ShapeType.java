package com.practice.enums;

public enum ShapeType {
    CIRCLE, RECTANGLE;
    public static ShapeType fromShape(Shape shape){
        return switch(shape){
            case Circle c->CIRCLE;
            case Rectangle r -> RECTANGLE;
        };
    }
}

sealed interface Shape permits Circle, Rectangle{}
record Circle(double radius) implements Shape{}
record Rectangle(double width, double height) implements Shape{}
