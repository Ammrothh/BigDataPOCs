package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.*;
import com.viewmanagementservice.handler.EventHandler;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.repository.LookupDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ViewManagementServiceImpl implements ViewManagementService {

    private final Map<EventType, EventHandler> eventHandlers;
    private final LookupDataRepository lookupDataRepository;

    public ViewManagementServiceImpl(List<EventHandler> eventHandlers, LookupDataRepository lookupDataRepository) {
        this.eventHandlers = eventHandlers.stream()
                .collect(Collectors.toMap(EventHandler::getEventType, Function.identity()));
        this.lookupDataRepository = lookupDataRepository;
    }

    @Override
    public void processEvent(Event event) {
        validateEvent(event);

        EventHandler eventHandler = eventHandlers.get(event.getEventType());
        if (eventHandler != null) {
            eventHandler.handle(event);
        } else {
            throw new IllegalArgumentException("No handler found for event type: " + event.getEventType());
        }
    }

    private void validateEvent(Event event) {
        if (event instanceof ResourceMetadataEvent) {
            validate(((ResourceMetadataEvent) event).getResourceName(), "resource name");
        } else if (event instanceof DapEvent) {
            validate(((DapEvent) event).getDapName(), "DAP name");
        } else if (event instanceof DerivedRuleEvent) {
            validate(((DerivedRuleEvent) event).getRuleName(), "rule name");
        } else if (event instanceof MigrationViewEvent) {
            validate(((MigrationViewEvent) event).getViewName(), "view name");
        } else if (event instanceof BackupTableViewEvent) {
            validate(((BackupTableViewEvent) event).getTableName(), "table name");
        }
    }

    private void validate(String key, String type) {
        if (!lookupDataRepository.existsById(key)) {
            throw new IllegalArgumentException("Invalid " + type + ": " + key);
        }
    }
}
