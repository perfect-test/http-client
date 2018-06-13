package com.perfecttest.http.logger;


import com.perfecttest.http.HttpRequest;
import com.perfecttest.http.HttpRequestMethod;

/**
 * Created by Sidelnikov Mikhail on 04.07.15.
 * Request logger interface
 */
public interface IRequestLogger {

    /**
     * logs request
     *
     * @param request       request object
     * @param requestMethod request method(GET,POST,DELETE etc)
     */
    void log(HttpRequest request, HttpRequestMethod requestMethod);

}
