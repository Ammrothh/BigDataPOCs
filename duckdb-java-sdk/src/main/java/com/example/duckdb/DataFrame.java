package com.example.duckdb;

/**
 * A DataFrame represents a relational dataset powered by DuckDB.
 * Operations on the DataFrame are lazily evaluated and pushed down to the
 * underlying DuckDB engine whenever possible.
 */
public interface DataFrame {

    /**
     * Selects a subset of columns.
     */
    DataFrame select(String... columns);

    /**
     * Filters the DataFrame based on a SQL-like condition.
     */
    DataFrame filter(String condition);

    /**
     * Maps the DataFrame to a new DataFrame using a SQL expression.
     * Higher-order function equivalent.
     */
    DataFrame map(String alias, String expression);

    /**
     * Aggregates the data.
     */
    DataFrame groupBy(String... columns);
    DataFrame agg(String expression);

    /**
     * Triggers execution and prints the result.
     */
    void show();

    /**
     * Returns the underlying SQL that will be executed.
     */
    String explain();

    /**
     * Export the current DataFrame state as an Apache Arrow Flight stream descriptor.
     */
    String toFlightDescriptor();
}
