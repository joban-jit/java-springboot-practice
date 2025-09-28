package com.practice.service;


import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.practice.util.Util.sleep;

@Service
public class EmailService {

    public void sendEmail(String recipient){
        sleep(2000);
        System.out.println("Email sent to "+recipient+" by "+Thread.currentThread());
    }

    public CompletableFuture<String> processData(String data){
        return CompletableFuture.supplyAsync(()->{
            sleep(1000);
            return "Processed: "+data;
        });
    }
}
