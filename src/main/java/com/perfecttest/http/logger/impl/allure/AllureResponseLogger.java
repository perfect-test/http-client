package com.perfecttest.http.logger.impl.allure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.perfecttest.http.HttpResponseEntity;
import com.perfecttest.http.logger.IResponseLogger;
import io.qameta.allure.Attachment;

/**
 * Created by Sidelnikov Mikhail on 06.07.15.
 * Response logger for allure
 */
public class AllureResponseLogger implements IResponseLogger {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void log(HttpResponseEntity responseEntity) {
        saveTextLog("Response log", responseEntity.toString());
    }


    @Attachment(value = "{attachName}", type = "text/plain")
    private static String saveSimpleTextLog(String attachName, String message) {
        if (message == null) {
            message = "null";
        }
        return message;
    }


    private static String saveTextLog(String attachName, String message) {
        try {
            GSON.toJson(GSON.fromJson(message, Object.class));
            return logJson(attachName, message);
        } catch (Exception e) {
            return saveSimpleTextLog(attachName, message);
        }
    }

    @Attachment(value = "{attachName}", type = "application/json")
    private static String logJson(String attachName, String message) {
        return message;
    }
}
