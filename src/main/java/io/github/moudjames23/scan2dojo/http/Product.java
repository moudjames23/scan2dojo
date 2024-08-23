package io.github.moudjames23.scan2dojo.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.moudjames23.scan2dojo.dto.requests.ProductRequest;
import io.github.moudjames23.scan2dojo.dto.Configuration;
import io.github.moudjames23.scan2dojo.enums.HttpMethod;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class Product implements Request {

    private final Configuration config;
    private final ProductRequest productRequest;

    public Product(Configuration config, ProductRequest productRequest) {
        this.config = config;
        this.productRequest = productRequest;
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
        return config.getEndpoint().concat("/api/v2/products/");
    }

    @Override
    public RequestBody getBody() {
        RequestBody requestBody;

        Map<String, Object> data = new HashMap<>();

        data.put("name", productRequest.name());
        data.put("description", productRequest.description());
        data.put("prod_type", productRequest.prod_type());
        data.put("sla_configuration", productRequest.sla_configuration());


        try {
            String json = new ObjectMapper().writeValueAsString(data);
            requestBody = RequestBody.create(json, MediaType.parse(APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Les données fournies sont incorrectes");
        }
        return requestBody;
    }
}
