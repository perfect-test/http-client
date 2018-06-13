package com.perfecttest.http;

import com.perfecttest.http.logger.IRequestLogger;
import com.perfecttest.http.logger.IResponseLogger;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ContentType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sidelnikov Mikhail on 25.06.15.
 * Http request builder
 */
public class HttpRequest {
    private List<RequestCookie> cookies;
    private List<RequestHeader> headers;
    private List<RequestParameter> parameters;
    private List<RequestBodyParameter> bodyParameters;
    private String url;
    private String hostName;
    private ContentType contentType;
    private HttpClient httpClient;
    private String body;
    private List<IRequestLogger> requestLoggers;
    private List<IResponseLogger> responseLoggers;
    private List<MultiPart> multiParts;
    private List<BinaryMultiPart> binaryMultiParts;
    private File file;
    private byte[] bytes;
    private HttpRequestMethod requestMethod;
    private ProxyConfig proxyConfig;

    public List<RequestCookie> getCookies() {
        return cookies;
    }

    public HttpRequestMethod getRequestMethod() {
        return requestMethod;
    }

    public List<RequestHeader> getHeaders() {
        return headers;
    }

    public List<RequestBodyParameter> getBodyParameters() {
        return bodyParameters;
    }

    public void cleanHeaders() {
        headers = new ArrayList<>();
    }

    /**
     * adds list of request parameters to request
     *
     * @param parameters list of RequestParameter objects
     */
    public void setParameters(List<RequestParameter> parameters) {
        this.parameters = parameters;
    }

    public List<RequestParameter> getParameters() {
        return parameters;
    }

    public String getUrl() {
        return url;
    }

    public String getHostName() {
        return hostName;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public String getBody() {
        return body;
    }

    public File getFile() {
        return file;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public List<MultiPart> getMultiParts() {
        return multiParts;
    }

    public List<BinaryMultiPart> getBinaryMultiParts() {
        return binaryMultiParts;
    }

    public ProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    /**
     * base method for build request
     *
     * @return HttpRequest object instance
     */
    public static HttpRequest build() {
        return new HttpRequest();
    }

    /**
     * sets specified Apache Http client instance for requests sending
     *
     * @param client instance of org.apache.http.client.HttpClient
     * @return HttpRequest instance
     */
    public HttpRequest setHttpClient(HttpClient client) {
        this.httpClient = client;
        return this;
    }

    /**
     * Sets string body to request
     *
     * @param body string body
     * @return HttpRequest instance
     */
    public HttpRequest setBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * sets url for request
     *
     * @param url url for request
     * @return HttpRequest instance
     */
    public HttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * sets file to send in multipart
     *
     * @param file file
     * @return HttpRequest instance
     */
    public HttpRequest setFile(File file) {
        this.file = file;
        return this;
    }

    /**
     * sets bytes to send in multipart
     *
     * @param bytes bytes array
     * @return HttpRequest instance
     */
    public HttpRequest setBytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }

    /**
     * add request logger(s)
     *
     * @param loggers list or single instance of IRequestLogger
     * @return HttpRequest instance
     */
    public HttpRequest withRequestLoggers(IRequestLogger... loggers) {
        if (this.requestLoggers == null) {
            this.requestLoggers = new ArrayList<>();
        }
        this.requestLoggers.addAll(Arrays.asList(loggers));
        return this;
    }

    /**
     * add response logger(s)
     *
     * @param loggers list or single instance of IResponceLogger
     * @return HttpRequest instance
     */
    public HttpRequest withResponseLoggers(IResponseLogger... loggers) {
        if (this.responseLoggers == null) {
            this.responseLoggers = new ArrayList<>();
        }
        this.responseLoggers.addAll(Arrays.asList(loggers));
        return this;
    }

    /**
     * sets content type for request
     *
     * @param contentType org.apache.http.entity.ContentType
     * @return HttpRequest instance
     */
    public HttpRequest setContentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * adds RequestHeaders's list for request
     *
     * @param headers list of RequestHeader objects
     * @return HttpRequest instance
     */
    public HttpRequest addHeaders(List<RequestHeader> headers) {
        if (headers != null) {
            for (RequestHeader requestHeader : headers) {
                addHeader(requestHeader);
            }
        }
        return this;
    }

    /**
     * adds RequestCookie's list for request
     *
     * @param cookies list of RequestCookie objects
     * @return HttpRequest instance
     */
    public HttpRequest addCookies(List<RequestCookie> cookies) {
        if (cookies != null) {
            for (RequestCookie cookie : cookies) {
                addCookie(cookie);
            }
        }
        return this;
    }

    /**
     * adds single request cookie for request
     *
     * @param cookie RequestCookie object
     * @return HttpRequest instance
     */
    public HttpRequest addCookie(RequestCookie cookie) {
        if (cookies == null) {
            cookies = new ArrayList<>();
        }
        cookies.add(cookie);
        return this;
    }

    /**
     * adds single request cookie for request by name and value
     *
     * @param name cookie name
     * @param value  cookie value
     * @return HttpRequest instance
     */
    public HttpRequest addCookie(String name, String value) {
        addCookie(new RequestCookie(name, value));
        return this;
    }

    /**
     * adds single request header for request
     *
     * @param header RequestHeader object
     * @return HttpRequest instance
     */
    public HttpRequest addHeader(RequestHeader header) {
        if (headers == null) {
            headers = new ArrayList<>();
        }
        headers.add(header);
        return this;
    }

    /**
     * adds single request header for request by name and value
     *
     * @param name header name
     * @param value  header value
     * @return HttpRequest instance
     */
    public HttpRequest addHeader(String name, String value) {
        addHeader(new RequestHeader(name, value));
        return this;
    }

    /**
     * adds single request parameter for request
     *
     * @param parameter RequestParameter object
     * @return HttpRequest instance
     */
    public HttpRequest addParameter(RequestParameter parameter) {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        parameters.add(parameter);
        return this;
    }

    /**
     * adds single multi part parameter for request by name and value
     *
     * @param name parameter name
     * @param value  parameter value
     * @return HttpRequest instance
     */
    public HttpRequest addMultiPart(String name, String value) {
        return addMultiPart(new MultiPart(name, value));
    }

    /**
     * adds single request body parameter for request
     *
     * @param parameter RequestBodyParameter object
     * @return HttpRequest instance
     */
    public HttpRequest addBodyParameter(RequestBodyParameter parameter){
        if (bodyParameters == null) {
            bodyParameters = new ArrayList<>();
        }
        bodyParameters.add(parameter);
        return this;
    }

    /**
     * adds single body parameter for request by name and value
     *
     * @param name parameter name
     * @param value  parameter value
     * @return HttpRequest instance
     */
    public HttpRequest addBodyParameter(String name, String value){
        return addBodyParameter(new RequestBodyParameter(name, value));
    }

    /**
     * clears all multiparts
     * @return HttpRequest instance
     */
    public HttpRequest clearMultiParts() {
        multiParts = null;
        return this;
    }

    /**
     * adds single multi part parameter for request
     *
     * @param multiPart MultiPart object
     * @return HttpRequest instance
     */
    public HttpRequest addMultiPart(MultiPart multiPart) {
        if (multiParts == null) {
            multiParts = new ArrayList<>();
        }
        multiParts.add(multiPart);
        return this;
    }

    /**
     * clears all binary multiparts
     * @return HttpRequest instance
     */
    public HttpRequest clearBinaryMultiParts() {
        binaryMultiParts = null;
        return this;
    }

    /**
     * adds single binary multi part parameter for request
     *
     * @param binaryMultiPart BinaryMultiPart object
     * @return HttpRequest instance
     */
    public HttpRequest addBinaryMultiPart(BinaryMultiPart binaryMultiPart) {
        if (binaryMultiParts == null) {
            binaryMultiParts = new ArrayList<>();
        }
        binaryMultiParts.add(binaryMultiPart);
        return this;
    }

    /**
     * adds MultiPart's list for request
     *
     * @param multiParts list of MultiPart objects
     * @return HttpRequest instance
     */
    public HttpRequest addMultiParts(List<MultiPart> multiParts) {
        if (multiParts != null) {
            for (MultiPart multiPart : multiParts) {
                addMultiPart(multiPart);
            }
        }
        return this;
    }

    /**
     * adds single request parameter by name and value
     *
     * @param name parameter name
     * @param value  parameter value
     * @return HttpRequest instance
     */
    public HttpRequest addParameter(String name, String value) {
        addParameter(new RequestParameter(name, value));
        return this;
    }

    public HttpRequest addParameter(String name, int value) {
        addParameter(new RequestParameter(name, Integer.toString(value)));
        return this;
    }

    /**
     * adds RequestParameter's list for request
     *
     * @param parameters list of RequestParameter objects
     * @return HttpRequest instance
     */
    public HttpRequest addParameters(List<RequestParameter> parameters) {
        if (parameters != null) {
            for (RequestParameter parameter : parameters) {
                addParameter(parameter);
            }
        }
        return this;
    }

    private void logRequest(HttpRequestMethod method) {
        if (requestLoggers != null) {
            for (IRequestLogger logger : requestLoggers) {
                logger.log(this, method);
            }
        }
    }

    private void logResponse(HttpResponseEntity entity) {
        if (responseLoggers != null) {
            for (IResponseLogger logger : responseLoggers) {
                logger.log(entity);
            }
        }
    }

    public HttpRequest removeParameter(RequestParameter requestParameter) {
        if (parameters != null && !parameters.isEmpty()) {
            parameters.remove(requestParameter);
        }
        return this;
    }

    public HttpRequest removeParameters() {
        if (parameters != null && !parameters.isEmpty()) {
            parameters = null;
        }
        return this;
    }

    public HttpRequest removeParameter(String parameterName) {
        return removeParameter(new RequestParameter().withName(parameterName));
    }

    public HttpRequest setParameterValue(String parameterName, String value) {
        removeParameter(parameterName);
        addParameter(parameterName, value);
        return this;
    }

    public String getFullRequestUrl() {
        return HttpUtils.getUrlWithParams(url, parameters);
    }

    /**
     * add proxy config to request
     * @param proxyConfig {@link ProxyConfig} instance - configuration for proxy
     * @return  HttpRequest instance
     */
    public HttpRequest withProxyConfig(ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
        return this;
    }


    public HttpResponseEntity sendPost() {
        return sendRequest(HttpRequestMethod.POST);
    }

    public HttpResponseEntity sendRequest(HttpRequestMethod method) {
        requestMethod = method;
        logRequest(requestMethod);
        HttpResponseEntity entity = HttpUtils.sendRequest(this);
        logResponse(entity);
        return entity;
    }

    /**
     * send PUT request
     * @return HttpResponseEntity object
     */
    public HttpResponseEntity sendPut() {
        return sendRequest(HttpRequestMethod.PUT);
    }

    /**
     * send GET request
     * @return HttpResponseEntity object
     */
    public HttpResponseEntity sendGet() {
        return sendRequest(HttpRequestMethod.GET);
    }

    /**
     * send DELETE request
     * @return HttpResponseEntity object
     */
    public HttpResponseEntity sendDelete() {
        return sendRequest(HttpRequestMethod.DELETE);
    }

    /**
     * send PATCH request
     * @return HttpResponseEntity object
     */
    public HttpResponseEntity sendPatch() {
        return sendRequest(HttpRequestMethod.PATCH);
    }

    /**
     * send multipart POST request
     * @return HttpResponseEntity object
     */
    public HttpResponseEntity sendMultipartPost() {
        return sendRequest(HttpRequestMethod.MULTIPART_POST);
    }

}
