package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.BackupTableViewEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class BackupTableViewEventHandler implements EventHandler<BackupTableViewEvent> {

    @Override
    public TrinoQueries handle(BackupTableViewEvent event) {
        // Business logic for handling Backup Table View Event
        System.out.println("Generating queries for Backup Table View Event: " + event.tableName());
        return new TrinoQueries("default", Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public EventType getEventType() {
        return EventType.BACKUP_TABLE_VIEW;
    }
}
