package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ResourceMetadataEventHandler implements EventHandler<ResourceMetadataEvent> {

    @Override
    public TrinoQueries handle(ResourceMetadataEvent event) {
        // Business logic for handling Resource Metadata Event
        System.out.println("Generating queries for Resource Metadata Event: " + event.resourceName());
        return new TrinoQueries(
                "default",
                Collections.singletonList("CREATE SCHEMA IF NOT EXISTS my_schema"),
                Collections.singletonList("CREATE OR REPLACE VIEW my_schema.my_view AS SELECT 1")
        );
    }

    @Override
    public EventType getEventType() {
        return EventType.RESOURCE_METADATA;
    }
}
