package com.perfecttest.libs;

import com.perfecttest.http.HttpRequest;
import com.perfecttest.http.logger.impl.ConsoleRequestLogger;
import com.perfecttest.http.logger.impl.ConsoleResponseLogger;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

/**
 * Created by mikesidelnikov on 20.12.16.
 */
public class RequestTest {


    @Test
    public void testGetRequest() {
        HttpRequest
                .build()
                .withRequestLoggers(new ConsoleRequestLogger())
                .withResponseLoggers(new ConsoleResponseLogger())
                .setUrl("http://yandex.ru")
                .sendGet()
                .checkCode(HttpStatus.SC_OK);
    }
}
