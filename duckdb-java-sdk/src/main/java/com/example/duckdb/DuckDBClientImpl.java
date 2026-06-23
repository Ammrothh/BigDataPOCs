package com.example.duckdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DuckDBClientImpl implements DuckDBClient {

    private Connection connection;

    public DuckDBClientImpl() throws SQLException {
        try {
            Class.forName("org.duckdb.DuckDBDriver");
            this.connection = DriverManager.getConnection("jdbc:duckdb:");
        } catch (ClassNotFoundException e) {
            throw new SQLException("DuckDB Driver not found", e);
        }
    }

    @Override
    public DataFrame sql(String query) {
        return new DataFrameImpl(this.connection, query);
    }

    @Override
    public DataFrame readCsv(String path) {
        return new DataFrameImpl(this.connection, "SELECT * FROM read_csv_auto('" + path + "')");
    }

    @Override
    public DataFrame readParquet(String path) {
        return new DataFrameImpl(this.connection, "SELECT * FROM read_parquet('" + path + "')");
    }

    @Override
    public void createTable(String tableName, String schema) {
        executeRaw("CREATE TABLE " + tableName + " (" + schema + ")");
    }

    @Override
    public void insert(String tableName, String values) {
        executeRaw("INSERT INTO " + tableName + " VALUES " + values);
    }

    @Override
    public void update(String tableName, String setClause, String condition) {
        String query = "UPDATE " + tableName + " SET " + setClause;
        if (condition != null && !condition.isEmpty()) {
            query += " WHERE " + condition;
        }
        executeRaw(query);
    }

    @Override
    public void delete(String tableName, String condition) {
        String query = "DELETE FROM " + tableName;
        if (condition != null && !condition.isEmpty()) {
            query += " WHERE " + condition;
        }
        executeRaw(query);
    }

    private void executeRaw(String query) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing statement: " + query, e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
