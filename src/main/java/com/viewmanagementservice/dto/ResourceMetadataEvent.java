package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public class ResourceMetadataEvent extends Event {
    @NotBlank(message = "Resource name cannot be blank")
    private String resourceName;

    public ResourceMetadataEvent() {
        setEventType(EventType.RESOURCE_METADATA);
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
