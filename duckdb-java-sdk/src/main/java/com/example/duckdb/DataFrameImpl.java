package com.example.duckdb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFrameImpl implements DataFrame {

    private final Connection connection;
    private final String query;

    public DataFrameImpl(Connection connection, String query) {
        this.connection = connection;
        this.query = query;
    }

    @Override
    public DataFrame select(String... columns) {
        String cols = String.join(", ", columns);
        String newQuery = String.format("SELECT %s FROM (%s) as subquery", cols, this.query);
        return new DataFrameImpl(this.connection, newQuery);
    }

    @Override
    public DataFrame filter(String condition) {
        String newQuery = String.format("SELECT * FROM (%s) as subquery WHERE %s", this.query, condition);
        return new DataFrameImpl(this.connection, newQuery);
    }

    @Override
    public DataFrame map(String alias, String expression) {
        String newQuery = String.format("SELECT *, (%s) AS %s FROM (%s) as subquery", expression, alias, this.query);
        return new DataFrameImpl(this.connection, newQuery);
    }

    @Override
    public DataFrame groupBy(String... columns) {
        String cols = String.join(", ", columns);
        String newQuery = String.format("SELECT %s FROM (%s) as subquery GROUP BY %s", cols, this.query, cols);
        return new DataFrameImpl(this.connection, newQuery);
    }

    @Override
    public DataFrame agg(String expression) {
        String newQuery = this.query.replace("FROM", ", " + expression + " FROM");
        return new DataFrameImpl(this.connection, newQuery);
    }

    @Override
    public void show() {
        System.out.println("Executing: " + this.query);
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(this.query)) {

            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getMetaData().getColumnName(i) + "\t");
            }
            System.out.println();

            int rows = 0;
            while (rs.next() && rows < 20) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
                rows++;
            }
            if (rs.next()) {
                System.out.println("...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> collect() {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(this.query)) {

            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Execution failed", e);
        }
        return results;
    }

    @Override
    public String explain() {
        return this.query;
    }

    @Override
    public String toFlightDescriptor() {
        return "FlightDescriptor: " + this.query;
    }
}
