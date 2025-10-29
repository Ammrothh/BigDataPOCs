package com.viewmanagementservice.client;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiClientTest {

    private MockWebServer mockWebServer;
    private ApiClient apiClient;

    @BeforeEach
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        apiClient = new ApiClient();
    }

    @AfterEach
    public void teardown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testGet() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"key\":\"value\"}"));
        String url = mockWebServer.url("/").toString();
        Map<String, String> headers = Collections.singletonMap("X-Test-Header", "test-value");

        String response = apiClient.get(url, headers);

        assertEquals("{\"key\":\"value\"}", response);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("test-value", recordedRequest.getHeader("X-Test-Header"));
    }

    @Test
    public void testPost() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"status\":\"success\"}"));
        String url = mockWebServer.url("/").toString();
        Map<String, String> headers = Collections.singletonMap("X-Test-Header", "test-value");
        String jsonBody = "{\"key\":\"value\"}";

        String response = apiClient.post(url, headers, jsonBody);

        assertEquals("{\"status\":\"success\"}", response);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("test-value", recordedRequest.getHeader("X-Test-Header"));
        assertEquals(jsonBody, recordedRequest.getBody().readUtf8());
    }
}
