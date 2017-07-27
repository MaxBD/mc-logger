package com.bd.logger.service;

import com.bd.logger.domain.GeneralAppLog;
import com.bd.logger.enumeration.GeneralAppLogSeverity;
import com.bd.logger.enumeration.GeneralAppLogType;
import com.bd.logger.enumeration.SearchByClass;
import com.bd.logger.repository.GeneralAppLogRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.omg.CORBA.Any;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.persistence.GenerationType;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static com.bd.logger.enumeration.GeneralAppLogType.PAGE_VISIT;
import static org.junit.Assert.*;

/**
 * Created by max on 7/23/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneralAppLogServiceTest {

    private static final Logger log = LoggerFactory.getLogger(GeneralAppLogServiceTest.class);

    private GeneralAppLogType generalAppLogType = GeneralAppLogType.PAGE_VISIT;
    private String description = "some description";
    private GeneralAppLogSeverity generalAppLogSeverity = GeneralAppLogSeverity.WARNING;
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    private String location = "/api";
    private String action = null;
    private String createdBy = "username";
    private String source = "Class";
    private Long itemsId = null;
    private SearchByClass searchByClass = SearchByClass.END_UNITS;
    private Long lreId = null;

    private String logString = generalAppLogType + ";" +
            description + ";" +
            generalAppLogSeverity + ";" +
            timestamp + ";" +
            location + ";" +
            action + ";" +
            createdBy + ";" +
            source + ";" +
            itemsId + ";" +
            searchByClass + ";" +
            lreId + ";";

    private GeneralAppLog generalAppLog = new GeneralAppLog(generalAppLogType,
            description,
            generalAppLogSeverity,
            timestamp,
            location,
            action,
            createdBy,
            source, itemsId,
            searchByClass,
            lreId);

    @Inject
    @Spy
    GeneralAppLogService generalAppLogService;

    @Inject
    GeneralAppLogRepository generalAppLogRepository;

    @Before
    public void setUp() throws Exception {
        generalAppLogRepository.deleteAll();
    }

    @Test
    public void parse_ShouldCreate_LogObject_FromString(){
        GeneralAppLog generalAppLog = generalAppLogService.parse(logString);
        assertEquals(generalAppLogType,  generalAppLog.getType());
        assertEquals(description, generalAppLog.getDescription());
        assertEquals(generalAppLogSeverity, generalAppLog.getSeverity());
        assertEquals(timestamp, generalAppLog.getTimeStamp());
        assertEquals(location, generalAppLog.getLocation());
        assertEquals(action, generalAppLog.getAction());
        assertEquals(createdBy, generalAppLog.getCreatedBy());
        assertEquals(source, generalAppLog.getSource());
        assertEquals(itemsId, generalAppLog.getItems_id());
        assertEquals(searchByClass, generalAppLog.getSearchByClass());
        assertEquals(lreId, generalAppLog.getLre_id());
    }

    @Test
    public void parse_ShouldReturnZero_IfStringHas_WrongNumberOfColumns(){
        GeneralAppLog generalAppLog = generalAppLogService.parse(logString + "new field;");
        assertNull(generalAppLog);
    }

    @Test
    public void stringNullOrEmpty_ShouldReturnTrue() {
        List<String> testStrings = Arrays.asList(null, "null", "");
        testStrings.forEach(string -> assertTrue(generalAppLogService.stringIsNullOrEmpty(string)));
    }

    @Test
    public void stringNullOrEmpty_ShouldReturnFalseIfFilled() {
        List<String> testStrings = Arrays.asList("some string", " ", "123124");
        testStrings.forEach(string -> assertFalse(generalAppLogService.stringIsNullOrEmpty(string)));
    }

    @Test
    public void getNumberOfColumnsInTable_ShouldReturnCorrectValue(){
        int count = generalAppLogService.getNumberOfColumnsInTable("general_app_log");
        assertEquals(12, count);
    }

    @Test
    public void getNumberOfColumnsInTable_ShouldReturnZero_ForNonExistentTable() {
        int count = generalAppLogService.getNumberOfColumnsInTable("wrong table");
        assertEquals(0, count);
    }

    @Test
    public void save_ShouldSaveLog_ToDataBase(){
        assertEquals(0, generalAppLogRepository.findAll().size());
        Mockito.doReturn(generalAppLog).when(generalAppLogService).parse(Mockito.any(String.class));
        generalAppLogService.save(logString);
        assertEquals(1, generalAppLogRepository.findAll().size());
        assertEquals(description, generalAppLogRepository.findAll().iterator().next().getDescription());
    }

    @Test
    public void save_ShouldDoNothing_IfAStringIsWrong(){
        assertEquals(0, generalAppLogRepository.findAll().size());
        Mockito.doReturn(null).when(generalAppLogService).parse(Mockito.any(String.class));
        generalAppLogService.save(logString);
        assertEquals(0, generalAppLogRepository.findAll().size());
    }

}