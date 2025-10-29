package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.MigrationViewEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MigrationViewEventHandler implements EventHandler<MigrationViewEvent> {

    @Override
    public TrinoQueries handle(MigrationViewEvent event) {
        // Business logic for handling Migration View Event
        System.out.println("Generating queries for Migration View Event: " + event.viewName());
        return new TrinoQueries("default", Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public EventType getEventType() {
        return EventType.MIGRATION_VIEW;
    }
}
