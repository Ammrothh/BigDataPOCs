package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.BackupTableViewEvent;
import com.viewmanagementservice.model.EventType;
import org.springframework.stereotype.Component;

@Component
public class BackupTableViewEventHandler implements EventHandler<BackupTableViewEvent> {

    @Override
    public void handle(BackupTableViewEvent event) {
        // Business logic for handling Backup Table View Event
        System.out.println("Handling Backup Table View Event: " + event.tableName());
    }

    @Override
    public EventType getEventType() {
        return EventType.BACKUP_TABLE_VIEW;
    }
}
