package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceMetadataEventHandlerTest {

    @Test
    public void testHandle() {
        ResourceMetadataEventHandler handler = new ResourceMetadataEventHandler();
        ResourceMetadataEvent event = new ResourceMetadataEvent("test-resource", EventType.RESOURCE_METADATA);

        TrinoQueries trinoQueries = handler.handle(event);

        assertEquals("default", trinoQueries.namespace());
        assertEquals(Collections.singletonList("CREATE SCHEMA IF NOT EXISTS my_schema"), trinoQueries.schemaQueries());
        assertEquals(Collections.singletonList("CREATE OR REPLACE VIEW my_schema.my_view AS SELECT 1"), trinoQueries.viewQueries());
    }
}
