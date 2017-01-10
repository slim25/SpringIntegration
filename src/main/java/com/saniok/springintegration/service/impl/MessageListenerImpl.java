package com.saniok.springintegration.service.impl;

import com.saniok.springintegration.model.Message;
import com.saniok.springintegration.service.MessageListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


/**
 * Created by Oleksandr_Tertyshnyi on 1/6/2017.
 */
@Service
public class MessageListenerImpl implements MessageListener {

    private static final Logger logger = Logger.getLogger(MessageListenerImpl.class);

    @Override
    public void processMessage(Message message) {
        logger.info("Received message: " + message);
        System.out.println("Received message: " + message.getBody());
    }
}
