package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public record MigrationViewEvent(
        @NotBlank(message = "View name cannot be blank")
        String viewName,
        EventType eventType
) implements Event {
    public MigrationViewEvent {
        if (eventType == null) {
            eventType = EventType.MIGRATION_VIEW;
        }
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }
}
