package com.bd.logger.service;

import com.bd.logger.config.StaticApplicationContext;
import com.bd.logger.service.exceptions.NullOrEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by max on 5/12/17.
 */
@Component
public class EventListenerService {

    private final String queueName = "LogQueue";
    private static final Logger log = LoggerFactory.getLogger(EventListenerService.class);

    @Bean
    Queue queue(){
        return new Queue(queueName, false);
    }

    @Async
    @RabbitListener(queues = queueName)
    public void onEvent(String e) throws NullOrEmptyException {
        log.trace("Received message: {} from queue: {}", e, queueName);
        ApplicationContext ctx = StaticApplicationContext.getContext();
        GeneralAppLogService generalAppLogService = ctx.getBean(GeneralAppLogService.class);
//        generalApplogService.process(e);
    }
}