package com.viewmanagementservice.handler;

import com.viewmanagementservice.dto.DapEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.trino.TrinoQueries;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Log4j2
public class DapEventHandler implements EventHandler<DapEvent> {

    @Override
    public TrinoQueries handle(DapEvent event) {
        log.info("Generating queries for DAP Event: {}", event.dapName());
        return new TrinoQueries("default", Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public EventType getEventType() {
        return EventType.DAP;
    }
}
