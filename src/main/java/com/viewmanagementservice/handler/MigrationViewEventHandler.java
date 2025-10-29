package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.MigrationViewEvent;
import com.viewmanagementservice.model.EventType;
import org.springframework.stereotype.Component;

@Component
public class MigrationViewEventHandler implements EventHandler<MigrationViewEvent> {

    @Override
    public void handle(MigrationViewEvent event) {
        // Business logic for handling Migration View Event
        System.out.println("Handling Migration View Event: " + event.viewName());
    }

    @Override
    public EventType getEventType() {
        return EventType.MIGRATION_VIEW;
    }
}
