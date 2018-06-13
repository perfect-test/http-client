package com.perfecttest.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringTokenizer;


public class CookieParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieParser.class);

    private static final String SET_COOKIE_HEADER_NAME = "Set-Cookie";

    /*
     * Parse cookie from 'Set-Cookie' header in response
     * Example: "Set-Cookie : OAID=a58bb5134a43c340c6a360b335bb931d; expires=Sat, 27-Jan-2018 15:58:17 GMT; path=/"
     */
    public static RequestCookie parseSetCookieHeader(RequestHeader setHeader, final String cookieName){

        if ( ! setHeader.getName().equalsIgnoreCase(SET_COOKIE_HEADER_NAME)){
            LOGGER.warn("Provider header (" + setHeader +") is not \"" + "Set-Cookie" + "\" header");
            return null;
        }

        String cookieMainPart = null;
        String cookieExpiresPart = null;
        String cookiePathPart = null;

        StringTokenizer tokenizer = new StringTokenizer(setHeader.getValue(), ";");
        while (tokenizer.hasMoreTokens()){
            String cookiePart = tokenizer.nextToken().trim();
            if (cookiePart.contains(cookieName)){
                cookieMainPart = cookiePart;
            } else if (cookiePart.toLowerCase().contains("path")){
                cookiePathPart = cookiePart;
            } else if (cookiePart.toLowerCase().contains("expires")){
                cookieExpiresPart = cookiePart;
            }
        }

        if (cookieMainPart == null){
            LOGGER.warn("Provider header (" + setHeader +") is not \"" + "Set-Cookie" + "\" header for cookie :" + cookieName);
            return null;
        }
        // Set up
        RequestCookie cookie = new RequestCookie(cookieName,cookieMainPart.substring(cookieName.length()+1));
        if (cookiePathPart != null){
            cookie = cookie.withPath(cookiePathPart.substring("path=".length()));
        }
        if (cookieExpiresPart != null){
            cookie = cookie.withExpires(cookieExpiresPart.substring("expires=".length()));
        }
        return cookie;
    }
}
