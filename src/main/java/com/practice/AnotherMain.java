package com.practice;

import com.practice.config.AppConfig;
import com.practice.config.PrototypeBean;
import com.practice.config.SingletonBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.springframework.util.Assert.isTrue;

public class AnotherMain {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        SingletonBean firstSingleton = context.getBean(SingletonBean.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeBean();

        // get singleton bean instance one more time
        SingletonBean secondSingleton = context.getBean(SingletonBean.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeBean();

        isTrue(firstPrototype.equals(secondPrototype), "The same instance should be returned");
    }
}
