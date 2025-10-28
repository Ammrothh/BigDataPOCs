package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;

public class DapEvent extends Event {
    private String dapName;

    public DapEvent() {
        setEventType(EventType.DAP);
    }

    public String getDapName() {
        return dapName;
    }

    public void setDapName(String dapName) {
        this.dapName = dapName;
    }
}
