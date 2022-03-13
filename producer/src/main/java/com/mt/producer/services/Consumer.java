package com.mt.producer.services;

import com.mt.producer.config.MessagingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Consumer {
    Logger logger = LoggerFactory.getLogger(MessagingConfig.class);


    @RabbitListener(queues = MessagingConfig.PRODUCER_QUEUE)
    public void consume(String msg){
        logger.info("message received in produced_queue from consumer service: "+msg);
    }
}