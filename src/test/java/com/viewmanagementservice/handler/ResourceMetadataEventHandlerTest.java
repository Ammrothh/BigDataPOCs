package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoConnectionManager;
import com.viewmanagementservice.trino.TrinoQueryExecutor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ResourceMetadataEventHandlerTest {

    @Mock
    private TrinoConnectionManager trinoConnectionManager;

    @Mock
    private TrinoQueryExecutor trinoQueryExecutor;

    @Test
    public void testHandle() {
        MockitoAnnotations.openMocks(this);
        ResourceMetadataEventHandler handler = new ResourceMetadataEventHandler(trinoConnectionManager, trinoQueryExecutor);
        ResourceMetadataEvent event = new ResourceMetadataEvent("test-resource", EventType.RESOURCE_METADATA);

        handler.handle(event);

        // In a real application, you would verify the business logic here
        // For now, we are just printing to the console
    }
}
