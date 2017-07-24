package com.bd.logger.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by max on 7/24/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventListenerServiceTest {

    private String queueName = "LogQueue";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void queue() throws Exception {
        EventListenerService eventListenerService = new EventListenerService();
        Queue queue = eventListenerService.queue();
        assertEquals(queue.getName(), queueName);
        assertFalse(queue.isDurable());
    }

    @Test
    public void onEvent() throws Exception {
    }

}