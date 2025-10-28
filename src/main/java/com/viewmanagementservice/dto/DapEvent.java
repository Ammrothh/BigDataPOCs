package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public class DapEvent extends Event {
    @NotBlank(message = "DAP name cannot be blank")
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
