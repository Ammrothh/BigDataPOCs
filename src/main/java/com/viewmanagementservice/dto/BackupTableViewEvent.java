package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;

public class BackupTableViewEvent extends Event {
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
