package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoConnectionManager;
import com.viewmanagementservice.trino.TrinoQueryExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ResourceMetadataEventHandler implements EventHandler<ResourceMetadataEvent> {

    private final TrinoConnectionManager trinoConnectionManager;
    private final TrinoQueryExecutor trinoQueryExecutor;

    @Override
    public void handle(ResourceMetadataEvent event) {
        try {
            Connection connection = trinoConnectionManager.getConnection("default");
            List<String> schemaQueries = Collections.singletonList("CREATE SCHEMA IF NOT EXISTS my_schema");
            List<String> viewQueries = Collections.singletonList("CREATE OR REPLACE VIEW my_schema.my_view AS SELECT 1");
            trinoQueryExecutor.executeQueries(connection, schemaQueries, viewQueries);
        } catch (Exception e) {
            // In a real application, you would handle this exception properly
            e.printStackTrace();
        }
    }

    @Override
    public EventType getEventType() {
        return EventType.RESOURCE_METADATA;
    }
}
