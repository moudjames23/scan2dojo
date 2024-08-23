package io.github.moudjames23.scan2dojo.http;

import io.github.moudjames23.scan2dojo.dto.requests.ImportRequest;
import io.github.moudjames23.scan2dojo.dto.Configuration;
import io.github.moudjames23.scan2dojo.enums.HttpMethod;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;

public class Import implements Request {


    private final Configuration config;
    private final ImportRequest importRequest;

    public Import(Configuration config, ImportRequest importRequest) {
        this.config = config;
        this.importRequest = importRequest;
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

    public String getUri() {
        return config.getEndpoint().concat("/api/v2/import-scan/");
    }

    @Override
    public RequestBody getBody() {
        File file = new File(importRequest.file());

       return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("actived", importRequest.active().toString())
                .addFormDataPart("verified", importRequest.verified().toString())
                .addFormDataPart("file", file.getName(), RequestBody.create(file, MediaType.parse("application/json")))
                .addFormDataPart("product_name", importRequest.productName())
                .addFormDataPart("scan_type", importRequest.scanType())
                .addFormDataPart("engagement_name", importRequest.engagementName())
                .addFormDataPart("minimum_severity", importRequest.minimumSeverity())
                .build();
    }
}
