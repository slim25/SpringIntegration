package com.saniok.springintegration.controller;

import com.saniok.springintegration.model.Message;
import com.saniok.springintegration.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Oleksandr_Tertyshnyi on 1/6/2017.
 */
@RestController
public class MessageController {

    @Autowired
    private PublishService publishService;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public void sendMessage(@RequestBody Message message, HttpServletResponse response){

        publishService.send(message);
        response.setStatus(HttpStatus.CREATED.value());
    }
}
