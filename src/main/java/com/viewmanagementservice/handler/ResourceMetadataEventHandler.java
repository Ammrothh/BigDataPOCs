package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.model.EventType;
import org.springframework.stereotype.Component;

@Component
public class ResourceMetadataEventHandler implements EventHandler<ResourceMetadataEvent> {

    @Override
    public void handle(ResourceMetadataEvent event) {
        // Business logic for handling Resource Metadata Event
        System.out.println("Handling Resource Metadata Event: " + event.resourceName());
    }

    @Override
    public EventType getEventType() {
        return EventType.RESOURCE_METADATA;
    }
}
