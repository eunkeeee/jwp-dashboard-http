package org.apache.coyote.http11.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HttpRequestStartLine {
    private static final Logger log = LoggerFactory.getLogger(HttpRequestStartLine.class);
    private static final int INDEX_REQUEST_URI = 1;
    private static final int INDEX_HTTP_METHOD = 0;
    private static final int INDEX_HTTP_VERSION = 2;
    private static final int REQUEST_START_LINE_SIZE = 3;

    private final HttpMethod httpMethod;
    private final String requestURI;
    private final String httpVersion;

    public HttpRequestStartLine(HttpMethod httpMethod, String requestURI, String httpVersion) {
        this.httpMethod = httpMethod;
        this.requestURI = requestURI;
        this.httpVersion = httpVersion;
    }

    public static HttpRequestStartLine from(String request) {
        List<String> requests = Arrays.stream(request.split(" ")).collect(Collectors.toList());
        validateRequestStartLineSize(request, requests);

        return new HttpRequestStartLine(
                HttpMethod.of(requests.get(INDEX_HTTP_METHOD)),
                requests.get(INDEX_REQUEST_URI),
                requests.get(INDEX_HTTP_VERSION)
        );
    }

    private static void validateRequestStartLineSize(String request, List<String> requests) {
        if (requests.size() != REQUEST_START_LINE_SIZE) {
            log.warn("Request Start Line {}이 형식에 맞지 않음", request);
            throw new IllegalStateException();
        }
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
}