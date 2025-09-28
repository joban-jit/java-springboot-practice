package com.practice.controller;


import com.practice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class MyController {

    @Autowired
    private EmailService emailService;


    @GetMapping("/send-email")
    public String sendEmail(){
        emailService.sendEmail("user@example.com");
        return "Email is being sent asynchronously";
    }

    @GetMapping("/process")
    public CompletableFuture<String> process(){
        return emailService.processData("Some data");
    }



}
