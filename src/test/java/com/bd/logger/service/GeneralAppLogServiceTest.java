package com.bd.logger.service;

import com.bd.logger.domain.GeneralAppLog;
import com.bd.logger.enumeration.GeneralAppLogSeverity;
import com.bd.logger.enumeration.SearchByClass;
import com.bd.logger.repository.GeneralAppLogRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.bd.logger.enumeration.GeneralAppLogType.PAGE_VISIT;
import static org.junit.Assert.*;

/**
 * Created by max on 7/23/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneralAppLogServiceTest {

    private static final Logger log = LoggerFactory.getLogger(GeneralAppLogServiceTest.class);
    private Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
    private GeneralAppLog generalAppLog = new GeneralAppLog(PAGE_VISIT,
                "some description",
                GeneralAppLogSeverity.ERROR,
                 Timestamp.valueOf(LocalDateTime.now()),
                "/api",
                null,
                "username",
                "Class",
                null,
                SearchByClass.END_UNITS,
                null);
    private String jsonString = gson.toJson(generalAppLog);

    @Inject
    GeneralAppLogService generalAppLogService;

    @Inject
    GeneralAppLogRepository generalAppLogRepository;


    @Before
    public void setUp() throws Exception {
        generalAppLogRepository.deleteAll();
    }

    @Test
    public void parse() throws Exception {
        GeneralAppLog generalAppLog = generalAppLogService.parse(jsonString);
        assertEquals(PAGE_VISIT, generalAppLog.getType());
    }

    @Test
    public void save() throws Exception {
        assertEquals(0, generalAppLogRepository.findAll().size());
        generalAppLogRepository.save(generalAppLog);
        assertEquals(1, generalAppLogRepository.findAll().size());

    }


}