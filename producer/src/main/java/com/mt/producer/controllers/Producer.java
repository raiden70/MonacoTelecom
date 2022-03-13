package com.mt.producer.controllers;

import com.mt.producer.config.MessagingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Producer {
    Logger logger = LoggerFactory.getLogger(MessagingConfig.class);

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/produce/{tag}")
    public String sendMessage(@PathVariable("tag") String tag){
        logger.info("[GET] Sendmessage is called with: "+tag);
        template.convertAndSend(MessagingConfig.CONSUMER_TEXCHANGE,MessagingConfig.CONSUMER_ROUTING_KEY,tag);
        logger.info("Consumer queue appended: "+tag);
        return "produced !";
    }
    @PostMapping("/produce")
    public String sendPostMessage(@RequestParam String tag){
        logger.info("[POST] sendPostMessage is called with: "+tag);
        template.convertAndSend(MessagingConfig.CONSUMER_TEXCHANGE,MessagingConfig.CONSUMER_ROUTING_KEY,tag);
        return tag;
    }

}
