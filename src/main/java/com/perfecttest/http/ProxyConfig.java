package com.perfecttest.http;

/**
 * class configuration for proxy
 */
public class ProxyConfig {
    private String proxyIp;
    private int proxyPort;

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public ProxyConfig withProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
        return this;
    }

    public ProxyConfig withProxyHost(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }


}
