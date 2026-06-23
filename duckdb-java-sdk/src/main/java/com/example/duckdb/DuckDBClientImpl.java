package com.example.duckdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DuckDBClientImpl implements DuckDBClient {

    private Connection connection;

    public DuckDBClientImpl() throws SQLException {
        // Initialize an in-memory DuckDB connection using the standard JDBC driver.
        // We will query via Arrow exports natively supported by the duckdb_jdbc driver.
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
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
