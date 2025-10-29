package com.viewmanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.viewmanagementservice.model.EventType;

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
public interface Event {
    EventType getEventType();
}
