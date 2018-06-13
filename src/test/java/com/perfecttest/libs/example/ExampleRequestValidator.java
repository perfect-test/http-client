package com.perfecttest.libs.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleRequestValidator {

    private ExampleResponse response;

    public ExampleRequestValidator(ExampleResponse response) {
        this.response = response;

    }

    public ExampleRequestValidator checkCode(int expectedCode) {
        int responseCode = response.getCode();
        assertTrue(responseCode == expectedCode,
                "Response code `" + responseCode + "` not equals to expected `" + expectedCode + "`");
        return this;
    }

    public ExampleRequestValidator checkBodyContains(String expectedString) {
        assertTrue(response.getBody().contains(expectedString),
                "Response body `" + response.getBody() + "` not contains expected `" + expectedString + "`");
        return this;
    }

    public ExampleRequestValidator checkBodyObject(ExampleResponseAsObject expectedObject) {
        assertTrue(expectedObject.equals(response.getResponseObject()),
                "Response body `" + response.getBody() + "` not contains expected `" + expectedObject + "`");
        return this;
    }
}
