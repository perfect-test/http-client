package com.perfecttest.http.logger.impl.allure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.perfecttest.http.CurlGenerator;
import com.perfecttest.http.HttpRequest;
import com.perfecttest.http.HttpRequestMethod;
import com.perfecttest.http.HttpUtils;
import com.perfecttest.http.logger.IRequestLogger;
import io.qameta.allure.Attachment;
import org.apache.tika.io.IOUtils;

import java.io.IOException;

/**
 * Created by Sidelnikov Mikhail on 04.07.15.
 * Http request logger for allure
 */
public class AllureRequestLogger implements IRequestLogger {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static void saveHtml(String json) {
        try {
            String html = IOUtils.toString(AllureRequestLogger.class.getResourceAsStream("/requestLoggerForm.html"),
                    "UTF-8");
            logHtml("Request log",
                    html.replace("{request_json}", json)
            );
        } catch (IOException e){
            throw new IllegalStateException("Error while reading request logger form from resources");
        }

    }

    @Attachment(value = "{attachName}", type = "text/html")
    private static String logHtml(String attachName, String html) {
        return html;
    }

    @Override
    public void log(HttpRequest request, HttpRequestMethod requestMethod) {
        saveHtml(requestToJson(request, requestMethod));
    }

    private String requestToJson(HttpRequest request, HttpRequestMethod requestMethod) {

        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("url", HttpUtils.getUrlWithParams(request.getUrl(), request.getParameters()));
            jsonObject.addProperty("curl", CurlGenerator.getCurl(request, requestMethod));
            jsonObject.addProperty("method", requestMethod.getMethodName());
            if (request.getHeaders() != null) {
                jsonObject.addProperty("headers", GSON.toJson(request.getHeaders()));
            }
            if (request.getCookies() != null) {
                jsonObject.addProperty("cookies", GSON.toJson(request.getCookies()));
            }
            if (request.getBody() != null) {
                jsonObject.addProperty("body", getFormattedBody(request.getBody()));
            }
            if (request.getContentType() != null) {
                jsonObject.addProperty("contentType", request.getContentType().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private String getFormattedBody(String body) {
        if (body == null) {
            return "";
        }
        try {
            return GSON.toJson(GSON.fromJson(body, Object.class));
        } catch (Exception e) {
            return body;
        }
    }
}
