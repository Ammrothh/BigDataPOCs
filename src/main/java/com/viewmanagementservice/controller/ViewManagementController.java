package com.viewmanagementservice.controller;

import com.viewmanagementservice.dto.Event;
import com.viewmanagementservice.service.ViewManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class ViewManagementController {

    private final ViewManagementService viewManagementService;

    @PostMapping
    public ResponseEntity<Void> processEvents(@Valid @RequestBody List<Event> events) {
        viewManagementService.processEvents(events);
        return ResponseEntity.accepted().build();
    }
}
