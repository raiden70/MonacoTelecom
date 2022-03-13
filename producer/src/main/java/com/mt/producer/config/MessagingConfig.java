package com.mt.producer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfig {
    Logger logger = LoggerFactory.getLogger(MessagingConfig.class);

    public static final String PRODUCER_QUEUE ="producer_queue";
    public static final String CONSUMER_QUEUE="consumer_queue";

    public static final String PRODUCER_TEXCHANGE="producer_exchange";
    public static final String CONSUMER_TEXCHANGE="consumer_exchange";

    public static final String PRODUCER_ROUTING_KEY ="producer_routing_key";
    public static final String CONSUMER_ROUTING_KEY="consumer_routing_key";

    @Bean
    public Queue producerQueue() {
        logger.info("producer_queue created.");
        return  new Queue(PRODUCER_QUEUE);
    }

    @Bean
    public Queue consumerQueue() {
        logger.info("consumer_queue created.");
        return  new Queue(CONSUMER_QUEUE);
    }
    @Bean
    public TopicExchange producerTExchange(){
        logger.info("producer_TE created.");
        return new TopicExchange(PRODUCER_TEXCHANGE);
    }
    @Bean
    public TopicExchange consumerTExchange(){
        logger.info("consumer_TE created.");
        return new TopicExchange(CONSUMER_TEXCHANGE);
    }
    @Bean
    public Binding producerBinding(Queue producerQueue, TopicExchange producerTExchange){
        logger.info("producerBinding: queue= "+producerQueue.getName() + " TE= " + producerTExchange.getName());
        return BindingBuilder.bind(producerQueue).to(producerTExchange).with(PRODUCER_ROUTING_KEY);
    }
    @Bean
    public Binding consumerBinding(Queue consumerQueue, TopicExchange consumerTExchange){
        logger.info("consumerBinding: queue= "+consumerQueue.getName() + " TE= " + consumerTExchange.getName());
        return BindingBuilder.bind(consumerQueue).to(consumerTExchange).with(CONSUMER_ROUTING_KEY);
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}

