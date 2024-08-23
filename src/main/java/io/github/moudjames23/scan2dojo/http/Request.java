package io.github.moudjames23.scan2dojo.http;

import io.github.moudjames23.scan2dojo.enums.HttpMethod;
import okhttp3.Headers;
import okhttp3.RequestBody;

public interface Request {


    String APPLICATION_JSON = "application/json";

    String BEARER = "Token ";

    default String getBaseUrl() {
        return "";
    }

    default String getAuthorization() {
        return "Authorization";
    }

    default String contentType()
    {
        return "application/json";
    }

    default Headers getHeaders() {
        return new Headers.Builder().build(); // Provide default empty headers
    }

    default RequestBody getBody() {
        return null;
    }

    default HttpMethod getMethod() {
        return HttpMethod.GET; // Provide a default HTTP method (e.g., GET)
    }

    default String getUri() {
        return ""; // Provide a default empty URI
    }
}
