package com.viewmanagementservice.trino;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class TrinoQueryExecutor {

    public void executeQueries(Connection connection, List<String> schemaQueries, List<String> viewQueries) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            for (String query : schemaQueries) {
                statement.execute(query);
            }
            for (String query : viewQueries) {
                statement.execute(query);
            }
        }
    }
}
