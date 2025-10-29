package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.*;
import com.viewmanagementservice.handler.EventHandler;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.repository.LookupDataRepository;
import com.viewmanagementservice.trino.TrinoConnectionManager;
import com.viewmanagementservice.trino.TrinoQueries;
import com.viewmanagementservice.trino.TrinoQueryExecutor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class AsyncEventProcessor {

    private final List<EventHandler> eventHandlerList;
    private final LookupDataRepository lookupDataRepository;
    private final TrinoConnectionManager trinoConnectionManager;
    private final TrinoQueryExecutor trinoQueryExecutor;

    private Map<EventType, EventHandler> eventHandlers;

    @PostConstruct
    public void init() {
        eventHandlers = eventHandlerList.stream()
                .collect(Collectors.toMap(EventHandler::getEventType, Function.identity()));
    }

    @Async("taskExecutor")
    public void processSingleEvent(Event event) {
        try {
            validateEvent(event);

            EventHandler eventHandler = eventHandlers.get(event.getEventType());
            if (eventHandler != null) {
                TrinoQueries trinoQueries = eventHandler.handle(event);
                Connection connection = trinoConnectionManager.getConnection(trinoQueries.namespace());
                trinoQueryExecutor.executeQueries(connection, trinoQueries.schemaQueries(), trinoQueries.viewQueries());
            } else {
                log.error("No handler found for event type: {}", event.getEventType());
            }
        } catch (SQLException e) {
            log.error("Error processing event: {}", event.getEventType(), e);
        }
    }

    private void validateEvent(Event event) {
        if (event instanceof ResourceMetadataEvent e) {
            validate(e.resourceName(), "resource name");
        } else if (event instanceof DapEvent e) {
            validate(e.dapName(), "DAP name");
        } else if (event instanceof DerivedRuleEvent e) {
            validate(e.ruleName(), "rule name");
        } else if (event instanceof MigrationViewEvent e) {
            validate(e.viewName(), "view name");
        } else if (event instanceof BackupTableViewEvent e) {
            validate(e.tableName(), "table name");
        }
    }

    private void validate(String key, String type) {
        if (!lookupDataRepository.existsById(key)) {
            throw new IllegalArgumentException("Invalid ".concat(type).concat(": ").concat(key));
        }
    }
}
