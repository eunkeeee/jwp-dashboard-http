package org.apache.coyote.http11.request;

import java.util.Arrays;

public enum HttpMethod {

    GET,
    POST,
    ;

    public static HttpMethod of(String httpMethod) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(httpMethod))
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }
}
