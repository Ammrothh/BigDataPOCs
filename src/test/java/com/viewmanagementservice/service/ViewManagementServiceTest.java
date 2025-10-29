package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.Event;
import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.model.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;

public class ViewManagementServiceTest {

    @Mock
    private AsyncEventProcessor asyncEventProcessor;

    private ViewManagementServiceImpl viewManagementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        viewManagementService = new ViewManagementServiceImpl(asyncEventProcessor);
    }

    @Test
    public void testProcessEvents() {
        List<Event> events = Collections.singletonList(new ResourceMetadataEvent("test-resource", EventType.RESOURCE_METADATA));

        viewManagementService.processEvents(events);

        verify(asyncEventProcessor).processSingleEvent(events.get(0));
    }
}
