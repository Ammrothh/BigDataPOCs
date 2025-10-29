package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.DapEvent;
import com.viewmanagementservice.model.EventType;
import org.springframework.stereotype.Component;

@Component
public class DapEventHandler implements EventHandler<DapEvent> {

    @Override
    public void handle(DapEvent event) {
        // Business logic for handling DAP Event
        System.out.println("Handling DAP Event: " + event.dapName());
    }

    @Override
    public EventType getEventType() {
        return EventType.DAP;
    }
}
