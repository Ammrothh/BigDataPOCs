package com.example.duckdb;

/**
 * Client to connect to DuckDB and create DataFrames.
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
}
