package com.example.duckdb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DuckDBClientTest {

    private DuckDBClient client;

    @Before
    public void setUp() throws Exception {
        client = new DuckDBClientImpl();
    }

    @After
    public void tearDown() throws Exception {
        if (client != null) {
            client.close();
        }
    }

    @Test
    public void testCrudOperations() {
        String tableName = "users";

        // 1. Create Table
        client.createTable(tableName, "id INTEGER, name VARCHAR, age INTEGER");

        // 2. Insert Data
        client.insert(tableName, "(1, 'Alice', 25), (2, 'Bob', 30), (3, 'Charlie', 35)");

        // 3. Read Data (Using DataFrame API)
        DataFrame df = client.sql("SELECT * FROM " + tableName);
        List<Map<String, Object>> rows = df.collect();
        assertEquals(3, rows.size());

        // 4. Update Data
        client.update(tableName, "age = 26", "id = 1");

        DataFrame aliceDf = client.sql("SELECT age FROM " + tableName + " WHERE id = 1");
        List<Map<String, Object>> aliceRows = aliceDf.collect();
        assertEquals(1, aliceRows.size());
        assertEquals(26, ((Number) aliceRows.get(0).get("age")).intValue());

        // 5. Delete Data
        client.delete(tableName, "id = 2");

        DataFrame remainingDf = client.sql("SELECT * FROM " + tableName);
        List<Map<String, Object>> remainingRows = remainingDf.collect();
        assertEquals(2, remainingRows.size());
    }

    @Test
    public void testHigherOrderFunctions() {
        String tableName = "products";
        client.createTable(tableName, "id INTEGER, price DOUBLE");
        client.insert(tableName, "(1, 10.0), (2, 20.0), (3, 30.0)");

        DataFrame df = client.sql("SELECT * FROM " + tableName);

        // Map: Apply a "function" (discount 10%)
        DataFrame discountedDf = df.map("discounted_price", "price * 0.9");
        List<Map<String, Object>> discountedRows = discountedDf.collect();
        assertEquals(3, discountedRows.size());

        // The last record has price 30.0, discounted should be 27.0
        double lastDiscountedPrice = ((Number) discountedRows.get(2).get("discounted_price")).doubleValue();
        assertEquals(27.0, lastDiscountedPrice, 0.001);

        // Filter: Only expensive products
        DataFrame expensiveDf = df.filter("price > 15.0");
        List<Map<String, Object>> expensiveRows = expensiveDf.collect();
        assertEquals(2, expensiveRows.size());
    }
}
