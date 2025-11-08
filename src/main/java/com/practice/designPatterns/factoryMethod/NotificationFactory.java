package com.practice.designPatterns.factoryMethod;

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
        notification.notifyUser("Hellooooo!");
    }
}
