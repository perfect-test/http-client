package com.perfecttest.libs.example;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class ExampleTest {


    @Test
    public void testMe(){
        new ExampleRequest()
                .withId("1")
                .sendRequest()
                .check()
                .checkCode(HttpStatus.SC_OK)
                .checkBodyContains("some string")
                .checkBodyObject(new ExampleResponseAsObject());
    }
}
