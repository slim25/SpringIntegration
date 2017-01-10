package com.saniok.springintegrationexample.service.impl;

import com.saniok.springintegrationexample.service.GreetingService;
import com.saniok.springintegrationexample.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by Oleksandr_Tertyshnyi on 1/5/2017.
 */
@Service
public class GreetingServiceImpl implements GreetingService{

    @Autowired
    private MessageChannel helloWorldChannel;
    @Autowired
    private HelloService helloWorldGateway;

    public void greet(String name) {
        helloWorldChannel.send(MessageBuilder.withPayload(name).build());
    }

    public void greetWithGateway(String name){
        System.out.println(helloWorldGateway.getHelloMessage(name));
    }
}
