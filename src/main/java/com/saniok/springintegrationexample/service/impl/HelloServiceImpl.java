package com.saniok.springintegrationexample.service.impl;

import com.saniok.springintegrationexample.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by Oleksandr_Tertyshnyi on 1/5/2017.
 */
@Service
public class HelloServiceImpl implements HelloService {

    public void hello(String name) {
        System.out.println("Hello : " + name);
    }

    @Override
    public String getHelloMessage(String name) {
        return "Hello : " + name;
    }
}
