package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.handler.EventHandler;
import com.viewmanagementservice.repository.LookupDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ViewManagementServiceValidationTest {

    @Mock
    private LookupDataRepository lookupDataRepository;

    @Mock
    private EventHandler eventHandler;

    private ViewManagementService viewManagementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        viewManagementService = new ViewManagementServiceImpl(Collections.singletonList(eventHandler), lookupDataRepository);
    }

    @Test
    public void testProcessEventWithInvalidResourceName() {
        ResourceMetadataEvent event = new ResourceMetadataEvent();
        event.setResourceName("invalid-resource");

        when(lookupDataRepository.existsById("invalid-resource")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> viewManagementService.processEvent(event));
    }
}
