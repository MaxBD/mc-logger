package com.bd.logger.service;

import com.bd.logger.domain.GeneralAppLog;
import com.bd.logger.enumeration.GeneralAppLogSeverity;
import com.bd.logger.enumeration.GeneralAppLogType;
import com.bd.logger.enumeration.SearchByClass;
import com.bd.logger.repository.GeneralAppLogRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.swing.*;
import java.sql.*;

/**
 * Created by max on 7/23/17.
 */
@Component
public class GeneralAppLogService {

    private static final Logger log = LoggerFactory.getLogger(GeneralAppLogService.class);
    private GeneralAppLogRepository generalAppLogRepository;

    @Named("dataSource")
    @Inject
    private DataSource dataSource;

    @Inject
    public GeneralAppLogService(GeneralAppLogRepository generalAppLogRepository) {
        this.generalAppLogRepository = generalAppLogRepository;
    }


    public void save(String logString) {
        GeneralAppLog generalAppLog = parse(logString);
        if (generalAppLog == null)
            return;
        generalAppLogRepository.save(generalAppLog);
    }

    public GeneralAppLog parse(String logString) {
        GeneralAppLog generalAppLog = new GeneralAppLog();
        String[] logColumns = logString.split(";");
        if (logColumns.length == getNumberOfColumnsInTable("general_app_log")-1){
            generalAppLog.setType(stringIsNullOrEmpty(logColumns[0]) ? null : GeneralAppLogType.valueOf(logColumns[0]));
            generalAppLog.setDescription(stringIsNullOrEmpty(logColumns[1]) ? null : logColumns[1]);
            generalAppLog.setSeverity(stringIsNullOrEmpty(logColumns[2]) ? null : GeneralAppLogSeverity.valueOf(logColumns[2]));
            generalAppLog.setTimeStamp(stringIsNullOrEmpty(logColumns[3]) ? null : Timestamp.valueOf(logColumns[3]));
            generalAppLog.setLocation(stringIsNullOrEmpty(logColumns[4]) ? null : logColumns[4]);
            generalAppLog.setAction(stringIsNullOrEmpty(logColumns[5]) ? null : logColumns[5]);
            generalAppLog.setCreatedBy(stringIsNullOrEmpty(logColumns[6]) ? null : logColumns[6]);
            generalAppLog.setSource(stringIsNullOrEmpty(logColumns[7]) ? null : logColumns[7]);
            generalAppLog.setItems_id(stringIsNullOrEmpty(logColumns[8])  ? null : Long.valueOf(logColumns[8]));
            generalAppLog.setSearchByClass(stringIsNullOrEmpty(logColumns[9]) ? null : SearchByClass.valueOf(logColumns[9]));
            generalAppLog.setLre_id(stringIsNullOrEmpty(logColumns[10])? null : Long.valueOf(logColumns[10]));
            return generalAppLog;
        }
        return null;
    }

    public boolean stringIsNullOrEmpty(String string){
        return (string == null || string.isEmpty() || string.equals("null"));
    }

    public int getNumberOfColumnsInTable(String tableName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("Select * FROM " + tableName);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData  = resultSet.getMetaData();
            return resultSetMetaData.getColumnCount();
        } catch (SQLException e) {
            log.debug("SQLException: {}", e);
        }
        return 0;
    }
}
