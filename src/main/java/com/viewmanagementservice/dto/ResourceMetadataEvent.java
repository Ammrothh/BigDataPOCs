package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public record ResourceMetadataEvent(
        @NotBlank(message = "Resource name cannot be blank")
        String resourceName,
        EventType eventType
) implements Event {
    public ResourceMetadataEvent {
        if (eventType == null) {
            eventType = EventType.RESOURCE_METADATA;
        }
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }
}
