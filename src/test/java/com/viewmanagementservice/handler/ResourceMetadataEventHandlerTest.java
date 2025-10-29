package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.model.EventType;
import org.junit.jupiter.api.Test;

public class ResourceMetadataEventHandlerTest {

    @Test
    public void testHandle() {
        ResourceMetadataEventHandler handler = new ResourceMetadataEventHandler();
        ResourceMetadataEvent event = new ResourceMetadataEvent("test-resource", EventType.RESOURCE_METADATA);

        handler.handle(event);

        // In a real application, you would verify the business logic here
        // For now, we are just printing to the console
    }
}
