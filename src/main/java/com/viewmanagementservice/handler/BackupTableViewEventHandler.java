package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.BackupTableViewEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Log4j2
public class BackupTableViewEventHandler implements EventHandler<BackupTableViewEvent> {

    @Override
    public TrinoQueries handle(BackupTableViewEvent event) {
        log.info("Generating queries for Backup Table View Event: {}", event.tableName());
        return new TrinoQueries("default", Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public EventType getEventType() {
        return EventType.BACKUP_TABLE_VIEW;
    }
}
