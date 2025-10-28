package com.viewmanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.viewmanagementservice.model.EventType;
import java.util.Objects;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "eventType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResourceMetadataEvent.class, name = "RESOURCE_METADATA"),
        @JsonSubTypes.Type(value = DapEvent.class, name = "DAP"),
        @JsonSubTypes.Type(value = DerivedRuleEvent.class, name = "DERIVED_RULE"),
        @JsonSubTypes.Type(value = MigrationViewEvent.class, name = "MIGRATION_VIEW"),
        @JsonSubTypes.Type(value = BackupTableViewEvent.class, name = "BACKUP_TABLE_VIEW")
})
public abstract class Event {
    private EventType eventType;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventType == event.eventType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType);
    }
}
