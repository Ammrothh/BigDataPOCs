package com.example.duckdb;

/**
 * Client to connect to DuckDB and create DataFrames,
 * as well as handle basic CRUD operations directly on the database.
 */
public interface DuckDBClient extends AutoCloseable {

    /**
     * Executes a raw SQL query and returns a DataFrame.
     */
    DataFrame sql(String query);

    /**
     * Loads a CSV file into a DataFrame.
     */
    DataFrame readCsv(String path);

    /**
     * Loads a Parquet file into a DataFrame.
     */
    DataFrame readParquet(String path);

    /**
     * Creates a new table with the given schema definition.
     * @param tableName Name of the table.
     * @param schema Definition of columns, e.g., "id INTEGER, name VARCHAR".
     */
    void createTable(String tableName, String schema);

    /**
     * Inserts records into the specified table.
     * @param tableName Name of the table.
     * @param values Values string, e.g., "(1, 'Alice'), (2, 'Bob')".
     */
    void insert(String tableName, String values);

    /**
     * Updates records in the specified table.
     * @param tableName Name of the table.
     * @param setClause Set clause, e.g., "name = 'Charlie'".
     * @param condition Where condition, e.g., "id = 1".
     */
    void update(String tableName, String setClause, String condition);

    /**
     * Deletes records from the specified table.
     * @param tableName Name of the table.
     * @param condition Where condition, e.g., "id = 1".
     */
    void delete(String tableName, String condition);
}
