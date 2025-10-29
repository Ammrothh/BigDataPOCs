package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.*;
import com.viewmanagementservice.handler.EventHandler;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.repository.LookupDataRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewManagementServiceImpl implements ViewManagementService {

    private final List<EventHandler> eventHandlerList;
    private final LookupDataRepository lookupDataRepository;
    private Map<EventType, EventHandler> eventHandlers;

    @PostConstruct
    public void init() {
        eventHandlers = eventHandlerList.stream()
                .collect(Collectors.toMap(EventHandler::getEventType, Function.identity()));
    }

    @Override
    public void processEvent(Event event) {
        validateEvent(event);

        EventHandler eventHandler = eventHandlers.get(event.getEventType());
        if (eventHandler != null) {
            eventHandler.handle(event);
        } else {
            throw new IllegalArgumentException("No handler found for event type: ".concat(event.getEventType().toString()));
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
