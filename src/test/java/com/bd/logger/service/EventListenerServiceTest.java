package com.bd.logger.service;

import com.bd.logger.repository.GeneralAppLogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by max on 7/24/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventListenerServiceTest {

    private String queueName = "LogQueue";

/*    @Inject
    EventListenerService eventListenerService;

    @Mock
    GeneralAppLogService generalAppLogService;*/

//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(EventListenerServiceTest.class);
//    }

    @Test
    public void queue_ShouldReturnRightQueue() throws Exception {
        EventListenerService eventListenerService = new EventListenerService();
        Queue queue = eventListenerService.queue();
        assertEquals(queue.getName(), queueName);
        assertFalse(queue.isDurable());
    }

//    @Test
//    public void onEvent_ShouldExecute_MethodSave() throws Exception {
//        eventListenerService.onEvent("Any String");
//        Mockito.verify(generalAppLogService, Mockito.times(1)).save(Mockito.any(String.class));
//    }
}