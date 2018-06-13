package com.perfecttest.http.logger.impl;

import com.perfecttest.http.HttpResponseEntity;
import com.perfecttest.http.logger.IResponseLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sidelnikov Mikhail on 06.07.15.
 * Console logger for http requests
 */
public class ConsoleResponseLogger implements IResponseLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleResponseLogger.class);

    public void log(HttpResponseEntity responseEntity) {
        LOGGER.info(responseEntity.toString());
    }
}
