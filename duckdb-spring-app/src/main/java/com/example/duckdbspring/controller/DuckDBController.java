package com.example.duckdbspring.controller;

import com.example.duckdbspring.service.DuckDBService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DuckDBController {

    private final DuckDBService duckDBService;

    public DuckDBController(DuckDBService duckDBService) {
        this.duckDBService = duckDBService;
    }

    @GetMapping("/user-orders")
    public List<Map<String, Object>> getUserOrders() {
        return duckDBService.getUserOrders();
    }
}
