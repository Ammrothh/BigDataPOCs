package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.DerivedRuleEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Log4j2
public class DerivedRuleEventHandler implements EventHandler<DerivedRuleEvent> {

    @Override
    public TrinoQueries handle(DerivedRuleEvent event) {
        log.info("Generating queries for Derived Rule Event: {}", event.ruleName());
        return new TrinoQueries("default", Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public EventType getEventType() {
        return EventType.DERIVED_RULE;
    }
}
