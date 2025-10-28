package com.viewmanagementservice.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewmanagementservice.client.ApiClient;
import com.viewmanagementservice.entity.LookupData;
import com.viewmanagementservice.repository.LookupDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CacheLoadingService implements CommandLineRunner {

    private final ApiClient apiClient;
    private final LookupDataRepository lookupDataRepository;
    private final ObjectMapper objectMapper;

    public CacheLoadingService(ApiClient apiClient, LookupDataRepository lookupDataRepository, ObjectMapper objectMapper) {
        this.apiClient = apiClient;
        this.lookupDataRepository = lookupDataRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        String json = apiClient.fetchLookupData();
        List<LookupData> lookupData = objectMapper.readValue(json, new TypeReference<List<LookupData>>() {});
        lookupDataRepository.saveAll(lookupData);
    }
}
