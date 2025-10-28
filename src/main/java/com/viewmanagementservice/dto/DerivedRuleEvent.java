package com.viewmanagementservice.dto;

import com.viewmanagementservice.model.EventType;

public class DerivedRuleEvent extends Event {
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
