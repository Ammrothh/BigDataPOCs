package com.viewmanagementservice.trino;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TrinoQueryExecutorTest {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    private TrinoQueryExecutor trinoQueryExecutor = new TrinoQueryExecutor();

    @Test
    public void testExecuteQueries() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.createStatement()).thenReturn(statement);

        List<String> schemaQueries = Arrays.asList("CREATE SCHEMA a", "CREATE SCHEMA b");
        List<String> viewQueries = Arrays.asList("CREATE VIEW v1", "CREATE VIEW v2");

        trinoQueryExecutor.executeQueries(connection, schemaQueries, viewQueries);

        InOrder inOrder = inOrder(statement);
        inOrder.verify(statement).execute("CREATE SCHEMA a");
        inOrder.verify(statement).execute("CREATE SCHEMA b");
        inOrder.verify(statement).execute("CREATE VIEW v1");
        inOrder.verify(statement).execute("CREATE VIEW v2");
    }
}
