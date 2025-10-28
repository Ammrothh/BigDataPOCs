package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;

public class ResourceMetadataEvent extends Event {
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
