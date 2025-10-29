package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public record BackupTableViewEvent(
        @NotBlank(message = "Table name cannot be blank")
        String tableName,
        EventType eventType
) implements Event {
    public BackupTableViewEvent {
        if (eventType == null) {
            eventType = EventType.BACKUP_TABLE_VIEW;
        }
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }
}
