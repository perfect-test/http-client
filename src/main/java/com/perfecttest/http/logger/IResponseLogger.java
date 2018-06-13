package com.perfecttest.http.logger;

import com.perfecttest.http.HttpResponseEntity;

/**
 * Created by Sidelnikov Mikhail on 06.07.15.
 * Reponse logger interface
 */
public interface IResponseLogger {
    /**
     * logs response
     *
     * @param responseEntity response entity
     */
    void log(HttpResponseEntity responseEntity);

}
