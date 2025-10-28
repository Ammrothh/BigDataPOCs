package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;

public class MigrationViewEvent extends Event {
    private String viewName;

    public MigrationViewEvent() {
        setEventType(EventType.MIGRATION_VIEW);
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
