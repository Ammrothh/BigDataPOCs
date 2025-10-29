package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.Event;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;

public interface EventHandler<T extends Event> {
    TrinoQueries handle(T event);
    EventType getEventType();
}
