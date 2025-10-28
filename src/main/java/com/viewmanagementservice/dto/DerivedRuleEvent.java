package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;
import jakarta.validation.constraints.NotBlank;

public class DerivedRuleEvent extends Event {
    @NotBlank(message = "Rule name cannot be blank")
    private String ruleName;

    public DerivedRuleEvent() {
        setEventType(EventType.DERIVED_RULE);
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
