package com.bd.logger.service;

import com.bd.logger.domain.GeneralAppLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import org.springframework.stereotype.Component;

/**
 * Created by max on 7/23/17.
 */
@Component
public class GeneralAppLogService {

    public GeneralAppLog parse(String stringJson) {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
        return gson.fromJson(stringJson, GeneralAppLog.class);
    }


}
