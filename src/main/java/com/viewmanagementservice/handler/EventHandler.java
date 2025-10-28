package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.Event;
import com.viewmanagementservice.model.EventType;

public interface EventHandler<T extends Event> {
    void handle(T event);
    EventType getEventType();
}
