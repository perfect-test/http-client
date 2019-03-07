package com.perfecttest.libs.example;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ExampleTest {


    @Test
    @Disabled("Just for example")
    public void testMe(){
        new ExampleRequest()
                .withId("1")
                .sendRequest()
                .check()
                .checkCode(HttpStatus.SC_OK)
                .checkBodyContains("DOCTYPE")
                .checkBodyObject(new ExampleResponseAsObject());
    }
}
