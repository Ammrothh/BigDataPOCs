package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewManagementServiceImpl implements ViewManagementService {

    private final AsyncEventProcessor asyncEventProcessor;

    @Override
    public void processEvents(List<Event> events) {
        for (Event event : events) {
            asyncEventProcessor.processSingleEvent(event);
        }
    }
}
