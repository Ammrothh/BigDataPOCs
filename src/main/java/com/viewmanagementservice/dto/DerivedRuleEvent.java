package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public record DerivedRuleEvent(
        @NotBlank(message = "Rule name cannot be blank")
        String ruleName,
        EventType eventType
) implements Event {
    public DerivedRuleEvent {
        if (eventType == null) {
            eventType = EventType.DERIVED_RULE;
        }
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }
}
