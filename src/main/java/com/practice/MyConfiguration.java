package com.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//@Configuration
public class MyConfiguration {


//    @Bean
//    public TaskExecutor taskExecutor(){
//        return new ConcurrentTaskExecutor(Executors.newVirtualThreadPerTaskExecutor());
//    }
}
