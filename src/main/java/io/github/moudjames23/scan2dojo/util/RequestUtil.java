package io.github.moudjames23.scan2dojo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.moudjames23.scan2dojo.dto.responses.ResponseMessage;
import io.github.moudjames23.scan2dojo.http.Request;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

import static io.github.moudjames23.scan2dojo.util.MessageUtil.*;

public class RequestUtil {

    private static final OkHttpClient httpClient = new OkHttpClient();

    private RequestUtil() {
    }

    public static  <T> void executeRequest(Request entity, Class<T> responseMessageClass) throws IOException {

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(entity.getUri())
                .headers(entity.getHeaders())
                .post(entity.getBody())
                .build();

         OkHttpClient okHttpClient = new OkHttpClient();

        try (Response response = okHttpClient.newCall(request).execute()) {
            assert response.body() != null;
            String data = response.body().string();

            if (!response.isSuccessful()) {
                printlnWithBorder(RED, data);
                return;
            }

            ResponseMessage message = (ResponseMessage) new ObjectMapper().readValue(data, responseMessageClass);

            printlnWithBorder(GREEN, message.success());
        }
        catch (Exception e)
        {
            printlnWithBorder(RED, e.getMessage());
        }
    }
}
