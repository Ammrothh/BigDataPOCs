package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.handler.EventHandler;
import com.viewmanagementservice.handler.ResourceMetadataEventHandler;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.repository.LookupDataRepository;
import com.viewmanagementservice.trino.TrinoConnectionManager;
import com.viewmanagementservice.trino.TrinoQueries;
import com.viewmanagementservice.trino.TrinoQueryExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class ViewManagementServiceTest {

    @Mock
    private ResourceMetadataEventHandler resourceMetadataEventHandler;

    @Mock
    private LookupDataRepository lookupDataRepository;

    @Mock
    private TrinoConnectionManager trinoConnectionManager;

    @Mock
    private TrinoQueryExecutor trinoQueryExecutor;

    @Mock
    private Connection connection;

    private ViewManagementServiceImpl viewManagementService;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(resourceMetadataEventHandler.getEventType()).thenReturn(EventType.RESOURCE_METADATA);
        List<EventHandler> handlers = Collections.singletonList(resourceMetadataEventHandler);
        viewManagementService = new ViewManagementServiceImpl(handlers, lookupDataRepository, trinoConnectionManager, trinoQueryExecutor);
        viewManagementService.init();
    }

    @Test
    public void testProcessEvent() throws Exception {
        ResourceMetadataEvent event = new ResourceMetadataEvent("test-resource", EventType.RESOURCE_METADATA);
        TrinoQueries trinoQueries = new TrinoQueries("default", Collections.singletonList("CREATE SCHEMA a"), Collections.singletonList("CREATE VIEW v1"));

        when(lookupDataRepository.existsById("test-resource")).thenReturn(true);
        when(resourceMetadataEventHandler.handle(event)).thenReturn(trinoQueries);
        when(trinoConnectionManager.getConnection("default")).thenReturn(connection);

        viewManagementService.processEvent(event);

        verify(trinoQueryExecutor).executeQueries(connection, trinoQueries.schemaQueries(), trinoQueries.viewQueries());
    }
}
