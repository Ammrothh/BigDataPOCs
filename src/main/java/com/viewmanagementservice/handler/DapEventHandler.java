package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.DapEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DapEventHandler implements EventHandler<DapEvent> {

    @Override
    public TrinoQueries handle(DapEvent event) {
        // Business logic for handling DAP Event
        System.out.println("Generating queries for DAP Event: " + event.dapName());
        return new TrinoQueries("default", Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public EventType getEventType() {
        return EventType.DAP;
    }
}
