package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public record DapEvent(
        @NotBlank(message = "DAP name cannot be blank")
        String dapName,
        EventType eventType
) implements Event {
    public DapEvent {
        if (eventType == null) {
            eventType = EventType.DAP;
        }
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }
}
