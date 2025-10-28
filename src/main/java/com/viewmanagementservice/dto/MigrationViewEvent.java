package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public class MigrationViewEvent extends Event {
    @NotBlank(message = "View name cannot be blank")
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
