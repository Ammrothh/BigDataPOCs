package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.handler.EventHandler;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.repository.LookupDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ViewManagementServiceValidationTest {

    @Mock
    private LookupDataRepository lookupDataRepository;

    @Mock
    private EventHandler eventHandler;

    private ViewManagementServiceImpl viewManagementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        List<EventHandler> handlers = Collections.singletonList(eventHandler);
        viewManagementService = new ViewManagementServiceImpl(handlers, lookupDataRepository);
        viewManagementService.init();
    }

    @Test
    public void testProcessEventWithInvalidResourceName() {
        ResourceMetadataEvent event = new ResourceMetadataEvent("invalid-resource", EventType.RESOURCE_METADATA);

        when(lookupDataRepository.existsById("invalid-resource")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> viewManagementService.processEvent(event));
    }
}
