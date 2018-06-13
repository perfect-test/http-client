package com.perfecttest.http;

import com.perfecttest.http.exception.SendingRequestException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by Sidelnikov Mikhail on 11.02.14.
 * Operations with http protocol
 */
public class HttpUtils {
    private static HttpClient client;

    private static HttpClient buildClient() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.setSSLContext(sslContext);
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        builder.setConnectionManager(connMgr);
        return builder.build();
    }

    public static boolean isResponseGood(HttpResponseEntity response) {
        if (response == null) {
            return false;
        }
        int code = response.getCode();
        return !(code == 404 || code == 503 || code == 500);
    }

    public static String getUrlWithParams(String url, List<RequestParameter> parameters) {
        if (parameters != null && !parameters.isEmpty()) {
            StringBuilder urlBuilder = new StringBuilder(url);
            if (!url.endsWith("?") || !url.contains("?")) {
                urlBuilder.append("?");
            }
            for (RequestParameter parameter : parameters) {
                urlBuilder.append(parameter.getName()).append("=").append(parameter.getValue()).append("&");
            }
            urlBuilder.setLength(urlBuilder.length() - 1);
            return urlBuilder.toString();
        }
        return url;
    }

    public static Map<String, List<String>> getParamsMapFromString(String url) {
        Map<String, List<String>> resultMap = new HashMap<>();
        List<String> paramsStringsList = getParamsStringsFromUrl(url);
        for (String paramString : paramsStringsList) {
            String key = getParameterKeyFromString(paramString);
            String value = getParameterValueFromString(paramString);
            List<String> valueFromMap = resultMap.get(key);
            if (valueFromMap == null) {
                valueFromMap = new ArrayList<>();
            }
            valueFromMap.add(value);
            resultMap.put(key, valueFromMap);
        }
        return resultMap;
    }

    private static String getParameterValueFromString(String paramString) {
        return paramString.substring(paramString.indexOf("=") + 1, paramString.length());
    }

    private static String getParameterKeyFromString(String paramString) {
        return paramString.substring(0, paramString.indexOf("="));
    }

    private static List<String> getParamsStringsFromUrl(String searchString) {
        if (searchString.contains("?")) {
            searchString = searchString.substring(searchString.indexOf("?") + 1, searchString.length());
        }
        return Arrays.asList(searchString.split("&"));
    }

    private static void setHeaders(HttpRequestBase requestBase, List<RequestHeader> headers) {
        if (headers != null) {
            for (RequestHeader header : headers) {
                requestBase.setHeader(header.getName(), header.getValue());
            }
        }
    }

    private static void setCookies(HttpRequestBase requestBase, List<RequestCookie> cookies) {
        if (cookies != null) {
            StringBuilder cookieValue = new StringBuilder();
            for (RequestCookie cookie : cookies) {
                cookieValue.append(cookie.getName()).append("=").append(cookie.getValue()).append("; ");
            }
            requestBase.setHeader("Cookie", cookieValue.toString().trim());
        }
    }

    private static void setBody(HttpEntityEnclosingRequestBase requestBase, String body, List<RequestBodyParameter> bodyParameters, ContentType contentType) {
        if (body != null) {
            if (contentType != null) {
                if (contentType.getCharset() == null) {
                    contentType = contentType.withCharset(Charset.forName("UTF-8"));
                }
            }
            requestBase.setEntity(new StringEntity(body, contentType));
        }
        if(bodyParameters != null && !bodyParameters.isEmpty()) {
            List<NameValuePair> postParameters = new ArrayList<>();
            for(RequestBodyParameter requestBodyParameter : bodyParameters){
                postParameters.add(new BasicNameValuePair(requestBodyParameter.getName(), requestBodyParameter.getValue()));
            }
            try {
                requestBase.setEntity(new UrlEncodedFormEntity(postParameters));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new SendingRequestException("Error while adding post body parameters");
            }
        }
    }

    private static void setFileBody(HttpEntityEnclosingRequestBase requestBase, File body, ContentType contentType) {
        if (body != null) {
            requestBase.setEntity(new FileEntity(body, contentType));
        }
    }

    private static void setBytesBody(HttpEntityEnclosingRequestBase requestBase, byte[] body, ContentType contentType) {
        if (body != null) {
            requestBase.setEntity(new ByteArrayEntity(body, contentType));
        }
    }


    public static HttpResponseEntity sendRequest(HttpRequest request) {
        String url = getUrlWithParams(request.getUrl(), request.getParameters());
        HttpEntityEnclosingRequestBase requestBase;
        HttpRequestMethod requestMethod = request.getRequestMethod();
        switch (requestMethod) {
            case GET:
                requestBase = new HttpGetWithEntity(url).withProxyConfig(request.getProxyConfig());
                break;
            case PATCH:
                requestBase = new HttpPatch(url);
                break;
            case PUT:
                requestBase = new HttpPut(url);
                break;
            case DELETE:
                requestBase = new HttpDeleteWithBody(url);
                break;
            case POST:
            case MULTIPART_POST:
                requestBase = new HttpPost(url);
                break;
            default:
                throw new IllegalStateException("Not supported method - " + requestMethod);
        }
        setHeaders(requestBase, request.getHeaders());
        ContentType contentType = request.getContentType();
        if (requestMethod.equals(HttpRequestMethod.POST)) {
            if (request.getFile() != null) {
                setFileBody(requestBase, request.getFile(), contentType);
            } else if (request.getBytes() != null) {
                setBytesBody(requestBase, request.getBytes(), contentType);
            } else {
                setBody(requestBase, request.getBody(), request.getBodyParameters(), contentType);
            }
        } else if (requestMethod.equals(HttpRequestMethod.MULTIPART_POST)) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            if (request.getMultiParts() != null) {
                for (MultiPart multiPart : request.getMultiParts()) {
                    builder.addTextBody(multiPart.getName(), multiPart.getValue());
                }
            }
            if (request.getBinaryMultiParts() != null) {
                for (BinaryMultiPart binaryMultiPart : request.getBinaryMultiParts()) {
                    builder.addBinaryBody(binaryMultiPart.getName(), binaryMultiPart.getFile(), binaryMultiPart.getContentType(), binaryMultiPart.getFileName());
                }
            }
            HttpEntity entity = builder.build();
            requestBase.setEntity(entity);
        } else {
            setBody(requestBase, request.getBody(), request.getBodyParameters(), contentType);
        }
        setCookies(requestBase, request.getCookies());
        setClient(request.getHttpClient());
        return sendRequest(requestBase, url);
    }

    private static void setClient(HttpClient httpClient) {
        if (httpClient != null) {
            client = httpClient;
        } else {
            client = buildClient();
        }
    }


    private static HttpResponseEntity sendRequest(HttpUriRequest request, String url) {
        HttpResponseEntity entity = new HttpResponseEntity();
        entity.setUrl(url);
        HttpResponse response;
        try {
            response = client.execute(request);
            entity.setCode(getResponseCode(response));
            entity.setResponseString(getStringFromResponse(response));
            entity.setHeaders(getHeadersFromResponse(response));
            request.abort();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SendingRequestException("Error while sending request : " + e.getMessage(), e);
        }
        return entity;
    }

    public static String getStringFromResponse(HttpResponse response) {
        try {
            if (response.getEntity() != null) {
                return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getResponseCode(HttpResponse response) {
        if (response == null) {
            return -1;
        } else {
            return response.getStatusLine().getStatusCode();
        }
    }

    private static List<RequestHeader> getHeadersFromResponse(HttpResponse response) {
        Header[] headers = response.getAllHeaders();
        if (headers != null && headers.length > 0) {
            List<RequestHeader> headersList = new ArrayList<>();
            for (Header header : headers) {
                headersList.add(new RequestHeader(header.getName(), header.getValue()));
            }
            return headersList;
        }
        return null;
    }


}
