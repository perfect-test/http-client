package com.perfecttest.http;

import org.apache.http.client.methods.HttpPost;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Sidelnikov Mikhail on 11.03.15.
 * Implementation for PATCH http request type
 */
public class HttpPatch extends HttpPost {

    public final static String METHOD_NAME = "PATCH";

    public HttpPatch(String url) {
        try {
            this.setURI(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error in url");
        }
    }

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
}
