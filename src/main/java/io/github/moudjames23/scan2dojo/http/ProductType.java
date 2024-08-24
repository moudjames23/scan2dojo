package io.github.moudjames23.scan2dojo.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.moudjames23.scan2dojo.dto.Configuration;
import io.github.moudjames23.scan2dojo.dto.requests.ProductTypeRequest;
import io.github.moudjames23.scan2dojo.enums.HttpMethod;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class ProductType implements Request{

    private final Configuration config;
    private final ProductTypeRequest productTypeRequest;

    public ProductType(Configuration config, ProductTypeRequest productRequest) {
        this.config = config;
        this.productTypeRequest = productRequest;
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
        return config.getEndpoint().concat("/api/v2/product_types/");
    }

    @Override
    public RequestBody getBody() {
        RequestBody requestBody;

        Map<String, Object> data = new HashMap<>();

        data.put("name", productTypeRequest.name());
        data.put("description", productTypeRequest.description());
        data.put("critical_product", productTypeRequest.criticalProduct());
        data.put("key_product", productTypeRequest.keyProduct());


        try {
            String json = new ObjectMapper().writeValueAsString(data);
            requestBody = RequestBody.create(json, MediaType.parse(APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Les données fournies sont incorrectes");
        }
        return requestBody;
    }
}
