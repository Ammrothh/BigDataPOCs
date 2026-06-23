package com.example.duckdb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Collectors;

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
        // This is a simplified group by builder, requires an agg call next
        String newQuery = String.format("SELECT %s FROM (%s) as subquery GROUP BY %s", cols, this.query, cols);
        return new DataFrameImpl(this.connection, newQuery);
    }

    @Override
    public DataFrame agg(String expression) {
        // Appends to the select list, assumes group by was applied previously or aggregates over all
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
            while (rs.next() && rows < 20) { // show top 20
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
    public String explain() {
        return this.query;
    }

    @Override
    public String toFlightDescriptor() {
        // Skeleton for Flight descriptor
        return "FlightDescriptor: " + this.query;
    }
}
