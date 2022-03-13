package com.mt.consumer.services;

import com.mt.consumer.config.MessagingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Consumer {
    Logger logger = LoggerFactory.getLogger(MessagingConfig.class);

    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = MessagingConfig.CONSUMER_QUEUE)
    public void consume(String msg){
        logger.info("message received in consumer_queue from producer service: "+msg);

        //RestTemplate restTemplate =new RestTemplate();
        //String s= restTemplate.getForObject("http://localhost:8080/services/"+msg,String.class);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/services/"+msg))
                    .GET()
                    .build();
            HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());

            if(response.statusCode()==200)
            {   // save the response into the producer queue
                template.convertAndSend(MessagingConfig.PRODUCER_TEXCHANGE,MessagingConfig.PRODUCER_ROUTING_KEY,response.body());
                logger.info("Producer queue appended: "+response.body());

            }
        }catch (Exception e)
        {
            logger.error("cannot do it: "+e);
        }
    }
}

