package com.viewmanagementservice.service;

import com.viewmanagementservice.dto.Event;

import java.util.List;

public interface ViewManagementService {
    void processEvents(List<Event> events);
}
