package com.practice.designPatterns.factoryMethod;
/*
The Factory Method Pattern Explained
Problem Solved: Avoids specifying the exact class of object to create. You use a factory method to create objects, making it easy to swap out implementations without changing client code
Classic Use-Case: When your application needs to support different types of objects with similar behavior or interface (e.g., different types of payment methods, notification channels, transport vehicles)
 */
interface Notification{
    void notifyUser(String message);
}

class EmailNotification implements Notification{

    @Override
    public void notifyUser(String message) {
        System.out.println("Email: "+message);
    }
}

class SMSNotification implements Notification {
    public void notifyUser(String message) {
        System.out.println("SMS: " + message);
    }
}


public class NotificationFactory {
    
    public Notification createNotification(String type){
        return switch(type){
            case "EMAIL"-> new EmailNotification();
            case "SMS"-> new SMSNotification();
            default-> throw new IllegalArgumentException("Unknown type: "+type);
        };
    }

    public static void main(String[] args) {
        NotificationFactory notificationFactory = new NotificationFactory();
        Notification notification = notificationFactory.createNotification("SMS");
        notification.notifyUser("Hello!");
    }
}
