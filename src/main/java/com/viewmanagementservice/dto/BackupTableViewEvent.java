package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public class BackupTableViewEvent extends Event {
    @NotBlank(message = "Table name cannot be blank")
    private String tableName;

    public BackupTableViewEvent() {
        setEventType(EventType.BACKUP_TABLE_VIEW);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
