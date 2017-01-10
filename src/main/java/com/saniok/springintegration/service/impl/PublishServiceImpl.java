package com.saniok.springintegration.service.impl;

import com.saniok.springintegration.model.Message;
import com.saniok.springintegration.service.PublishService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by Oleksandr_Tertyshnyi on 1/6/2017.
 */
@Service
public class PublishServiceImpl implements PublishService {

    private static final Logger logger = Logger.getLogger( PublishServiceImpl.class );

    @Autowired
    private MessageChannel topicChannel;

    @Override
    public void send(Message message) {
        logger.info("Sending message to message channel: " + message.toString());
        topicChannel.send(MessageBuilder.withPayload(message).build());
    }
}
