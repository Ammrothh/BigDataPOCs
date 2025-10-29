package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.DerivedRuleEvent;
import com.viewmanagementservice.model.EventType;
import org.springframework.stereotype.Component;

@Component
public class DerivedRuleEventHandler implements EventHandler<DerivedRuleEvent> {

    @Override
    public void handle(DerivedRuleEvent event) {
        // Business logic for handling Derived Rule Event
        System.out.println("Handling Derived Rule Event: " + event.ruleName());
    }

    @Override
    public EventType getEventType() {
        return EventType.DERIVED_RULE;
    }
}
