package com.viewmanagementservice.client;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
public class ApiClient {

    private final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public String fetchLookupData() throws IOException {
        return get("https://api.example.com/lookup-data", Collections.emptyMap());
    }

    public String get(String url, Map<String, String> headers) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        headers.forEach(requestBuilder::addHeader);
        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public String post(String url, Map<String, String> headers, String jsonBody) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        headers.forEach(requestBuilder::addHeader);
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = requestBuilder.post(body).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
