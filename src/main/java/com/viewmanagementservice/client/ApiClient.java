package com.viewmanagementservice.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiClient {

    private final OkHttpClient client = new OkHttpClient();

    public String fetchLookupData() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.example.com/lookup-data")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
