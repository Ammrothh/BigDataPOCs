package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.MigrationViewEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Log4j2
public class MigrationViewEventHandler implements EventHandler<MigrationViewEvent> {

    @Override
    public TrinoQueries handle(MigrationViewEvent event) {
        log.info("Generating queries for Migration View Event: {}", event.viewName());
        return new TrinoQueries("default", Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public EventType getEventType() {
        return EventType.MIGRATION_VIEW;
    }
}
