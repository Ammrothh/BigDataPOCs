package com.viewmanagementservice.controller;

import com.viewmanagementservice.dto.Event;
import com.viewmanagementservice.service.ViewManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class ViewManagementController {

    private final ViewManagementService viewManagementService;

    public ViewManagementController(ViewManagementService viewManagementService) {
        this.viewManagementService = viewManagementService;
    }

    @PostMapping
    public ResponseEntity<Void> processEvent(@RequestBody Event event) {
        viewManagementService.processEvent(event);
        return ResponseEntity.ok().build();
    }
}
