package io.github.moudjames23.scan2dojo.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.moudjames23.scan2dojo.dto.requests.EngagementRequest;
import io.github.moudjames23.scan2dojo.dto.Configuration;
import io.github.moudjames23.scan2dojo.enums.HttpMethod;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class Engagement implements Request {

    private final Configuration config;
    private final EngagementRequest engagementRequest;

    public Engagement(Configuration config, EngagementRequest engagementRequest) {
        this.config = config;
        this.engagementRequest = engagementRequest;
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public Headers getHeaders() {
        return new Headers.Builder()
                .add(getAuthorization(), BEARER.concat(this.config.getApiKey()))
                .add("Content-Type", APPLICATION_JSON)
                .build();
    }

    @Override
    public String getUri() {
        return config.getEndpoint().concat("/api/v2/engagements/");
    }

    @Override
    public RequestBody getBody() {
        RequestBody requestBody;

        Map<String, Object> data = new HashMap<>();

        data.put("name", engagementRequest.name());
        data.put("description", engagementRequest.description());
        data.put("target_start", engagementRequest.start());
        data.put("target_end", engagementRequest.end());
        data.put("product", engagementRequest.productId());


        try {
            String json = new ObjectMapper().writeValueAsString(data);
            requestBody = RequestBody.create(json, MediaType.parse(APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Les données fournies sont incorrectes");
        }
        return requestBody;
    }
}
