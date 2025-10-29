package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.DerivedRuleEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DerivedRuleEventHandler implements EventHandler<DerivedRuleEvent> {

    @Override
    public TrinoQueries handle(DerivedRuleEvent event) {
        // Business logic for handling Derived Rule Event
        System.out.println("Generating queries for Derived Rule Event: " + event.ruleName());
        return new TrinoQueries("default", Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public EventType getEventType() {
        return EventType.DERIVED_RULE;
    }
}
