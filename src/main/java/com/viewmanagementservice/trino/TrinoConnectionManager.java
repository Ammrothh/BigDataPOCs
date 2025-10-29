package com.viewmanagementservice.trino;

import io.trino.jdbc.TrinoDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TrinoConnectionManager {

    @Value("${trino.url}")
    private String trinoUrl;

    @Value("${trino.user}")
    private String trinoUser;

    private final Map<String, Connection> connectionPool = new ConcurrentHashMap<>();

    public Connection getConnection(String namespace) throws SQLException {
        Connection connection = connectionPool.get(namespace);
        if (connection == null || connection.isClosed()) {
            synchronized (this) {
                connection = connectionPool.get(namespace);
                if (connection == null || connection.isClosed()) {
                    connection = createNewConnection(namespace);
                    connectionPool.put(namespace, connection);
                }
            }
        }
        return connection;
    }

    private Connection createNewConnection(String namespace) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", trinoUser);
        properties.setProperty("SSL", "true");

        String url = String.format("%s/%s", trinoUrl, namespace);
        DriverManager.registerDriver(new TrinoDriver());
        return DriverManager.getConnection(url, properties);
    }
}
