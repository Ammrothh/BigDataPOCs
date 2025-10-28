package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.handler.EventHandler;
import com.viewmanagementservice.handler.ResourceMetadataEventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewManagementServiceTest {

    @Mock
    private ResourceMetadataEventHandler resourceMetadataEventHandler;

    private ViewManagementService viewManagementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(resourceMetadataEventHandler.getEventType()).thenReturn(com.viewmanagementservice.model.EventType.RESOURCE_METADATA);
        viewManagementService = new ViewManagementServiceImpl(Collections.singletonList(resourceMetadataEventHandler));
    }

    @Test
    public void testProcessEvent() {
        ResourceMetadataEvent event = new ResourceMetadataEvent();
        event.setResourceName("test-resource");

        viewManagementService.processEvent(event);

        verify(resourceMetadataEventHandler).handle(event);
    }
}
