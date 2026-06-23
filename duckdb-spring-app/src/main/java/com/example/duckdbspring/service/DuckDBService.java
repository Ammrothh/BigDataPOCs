package com.example.duckdbspring.service;

import com.example.duckdb.DuckDBClient;
import com.example.duckdb.DuckDBClientImpl;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class DuckDBService {

    private DuckDBClient client;

    @PostConstruct
    public void init() {
        try {
            client = new DuckDBClientImpl();
            setupDummyData();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize DuckDB client", e);
        }
    }

    private void setupDummyData() {
        // Create users table
        client.createTable("users", "user_id INTEGER, name VARCHAR, email VARCHAR");
        client.insert("users",
                "(1, 'John Doe', 'john@example.com'), " +
                "(2, 'Jane Smith', 'jane@example.com'), " +
                "(3, 'Alice Johnson', 'alice@example.com')");

        // Create orders table
        client.createTable("orders", "order_id INTEGER, user_id INTEGER, product VARCHAR, amount DOUBLE");
        client.insert("orders",
                "(101, 1, 'Laptop', 1200.50), " +
                "(102, 1, 'Mouse', 25.00), " +
                "(103, 2, 'Keyboard', 75.00), " +
                "(104, 3, 'Monitor', 300.00)");
    }

    public List<Map<String, Object>> getUserOrders() {
        // Performing the JOIN using the client directly
        String joinQuery = "SELECT u.name, u.email, o.product, o.amount " +
                           "FROM users u " +
                           "JOIN orders o ON u.user_id = o.user_id";

        return client.sql(joinQuery).collect();
    }

    @PreDestroy
    public void cleanup() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
