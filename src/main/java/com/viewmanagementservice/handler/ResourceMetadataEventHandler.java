package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Log4j2
public class ResourceMetadataEventHandler implements EventHandler<ResourceMetadataEvent> {

    @Override
    public TrinoQueries handle(ResourceMetadataEvent event) {
        log.info("Generating queries for Resource Metadata Event: {}", event.resourceName());
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
