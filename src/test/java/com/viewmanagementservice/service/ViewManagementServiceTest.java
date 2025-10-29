package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.handler.EventHandler;
import com.viewmanagementservice.handler.ResourceMetadataEventHandler;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.repository.LookupDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewManagementServiceTest {

    @Mock
    private ResourceMetadataEventHandler resourceMetadataEventHandler;

    @Mock
    private LookupDataRepository lookupDataRepository;

    private ViewManagementServiceImpl viewManagementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(resourceMetadataEventHandler.getEventType()).thenReturn(EventType.RESOURCE_METADATA);
        List<EventHandler> handlers = Collections.singletonList(resourceMetadataEventHandler);
        viewManagementService = new ViewManagementServiceImpl(handlers, lookupDataRepository);
        viewManagementService.init();
    }

    @Test
    public void testProcessEvent() {
        ResourceMetadataEvent event = new ResourceMetadataEvent("test-resource", EventType.RESOURCE_METADATA);

        when(lookupDataRepository.existsById("test-resource")).thenReturn(true);

        viewManagementService.processEvent(event);

        verify(resourceMetadataEventHandler).handle(event);
    }
}
