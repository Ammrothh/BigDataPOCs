package com.example.duckdb;

import java.util.List;
import java.util.Map;

/**
 * A DataFrame represents a relational dataset powered by DuckDB.
 */
public interface DataFrame {

    DataFrame select(String... columns);

    DataFrame filter(String condition);

    DataFrame map(String alias, String expression);

    DataFrame groupBy(String... columns);
    DataFrame agg(String expression);

    /**
     * Executes the query and prints to stdout.
     */
    void show();

    /**
     * Executes the query and returns results as a list of maps (rows to column-value mapping).
     */
    List<Map<String, Object>> collect();

    String explain();

    String toFlightDescriptor();
}
