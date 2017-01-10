package com.saniok.springintegration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saniok.springintegration.service.MessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.integration.jms.ChannelPublishingJmsMessageListener;
import org.springframework.integration.jms.DynamicJmsTemplate;
import org.springframework.integration.jms.JmsSendingMessageHandler;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * Created by Oleksandr_Tertyshnyi on 1/6/2017.
 */
@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableIntegration
public class MainApplication {

    @Autowired
    private ActiveMQConnectionFactory factory;
    @Autowired
    private ChannelPublishingJmsMessageListener listener;
    @Autowired
    private MessageListener listenerHandler;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean(name = "topicChannel")
    public MessageChannel messageChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean(name = "listenerChannel")
    public MessageChannel listenerChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "listenerChannel")
    public MessageHandler messageHandler() {
        return new MethodInvokingMessageHandler(listenerHandler, "processMessage");
    }

    @Bean(name = "connectionFactory")
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "topicChannel")
    public MessageHandler jsmOutboundAdapter() {
        JmsTemplate template = new DynamicJmsTemplate();
        template.setConnectionFactory(factory);
        template.setPubSubDomain(true);
        template.setDefaultDestinationName("topic.myTopic");
        JmsSendingMessageHandler handler = new JmsSendingMessageHandler(template);

        return handler;
    }

    @Bean
    @Autowired
    public DefaultMessageListenerContainer container() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setDestinationName("topic.myTopic");
        container.setPubSubDomain(true);
        container.setMessageListener(listener);
        return container;
    }

    @Bean
    public ChannelPublishingJmsMessageListener messageListener(){
        ChannelPublishingJmsMessageListener adapter = new ChannelPublishingJmsMessageListener();
        adapter.setRequestChannelName("listenerChannel");
        return adapter;
    }


    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

        return mapper;
    }
}
