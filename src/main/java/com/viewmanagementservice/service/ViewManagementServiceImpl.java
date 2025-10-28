package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.Event;
import com.viewmanagementservice.handler.EventHandler;
import com.viewmanagementservice.model.EventType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ViewManagementServiceImpl implements ViewManagementService {

    private final Map<EventType, EventHandler> eventHandlers;

    public ViewManagementServiceImpl(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers.stream()
                .collect(Collectors.toMap(EventHandler::getEventType, Function.identity()));
    }

    @Override
    public void processEvent(Event event) {
        EventHandler eventHandler = eventHandlers.get(event.getEventType());
        if (eventHandler != null) {
            eventHandler.handle(event);
        } else {
            throw new IllegalArgumentException("No handler found for event type: " + event.getEventType());
        }
    }
}
