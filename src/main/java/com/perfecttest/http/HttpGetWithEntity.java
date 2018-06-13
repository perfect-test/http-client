package com.perfecttest.http;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Sidelnikov Mikhail on 02.03.15.
 * Implementation for http GET request with body
 */
public class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {
    private final static String METHOD_NAME = "GET";

    public HttpGetWithEntity(String url) {
        try {
            this.setURI(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error in url");
        }
    }

    public HttpGetWithEntity withProxyConfig(ProxyConfig proxyConfig) {
        if (proxyConfig != null) {
            this.setConfig(RequestConfig.custom()
                    .setProxy(new HttpHost(proxyConfig.getProxyIp(), proxyConfig.getProxyPort()))
                    .build());
        }
        return this;
    }

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
}

