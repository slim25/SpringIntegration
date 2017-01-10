package com.saniok.springintegrationexample.main;

import com.saniok.springintegrationexample.service.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Oleksandr_Tertyshnyi on 1/5/2017.
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        GreetingService service = applicationContext.getBean("greetingServiceImpl", GreetingService.class);
        service.greet("Spring Integration!");

        service.greetWithGateway( "Spring Integration (with response)!");

    }
}
