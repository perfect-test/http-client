package com.perfecttest.libs.example;

import com.perfecttest.http.HttpRequest;
import com.perfecttest.http.HttpResponseEntity;
import com.perfecttest.http.logger.impl.ConsoleRequestLogger;
import com.perfecttest.http.logger.impl.ConsoleResponseLogger;
import com.perfecttest.http.logger.impl.allure.AllureRequestLogger;
import com.perfecttest.http.logger.impl.allure.AllureResponseLogger;
import org.apache.http.entity.ContentType;

public class ExampleRequest {
    private HttpRequest request;

    public ExampleRequest() {
        request = HttpRequest
                .build()
                .withRequestLoggers(new AllureRequestLogger(), new ConsoleRequestLogger())
                .withResponseLoggers(new AllureResponseLogger(), new ConsoleResponseLogger());
    }

    public ExampleRequest withId(String id){
        request.addParameter("id", id);
        return this;
    }

    public ExampleRequest withContentType(ContentType type){
        request.setContentType(type);
        return this;
    }

    public ExampleResponse sendRequest(){
        HttpResponseEntity responseEntity = request.setUrl("http://yandex.ru").sendGet();
        return new ExampleResponse(responseEntity.getCode(), responseEntity.getResponseString());
    }


}
