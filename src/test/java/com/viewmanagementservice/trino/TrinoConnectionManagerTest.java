package com.viewmanagementservice.trino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class TrinoConnectionManagerTest {

    private TrinoConnectionManager trinoConnectionManager;

    @Mock
    private Connection connection;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        trinoConnectionManager = new TrinoConnectionManager();
        ReflectionTestUtils.setField(trinoConnectionManager, "trinoUrl", "jdbc:trino://example.com:443");
        ReflectionTestUtils.setField(trinoConnectionManager, "trinoUser", "admin");
    }

    @Test
    public void testGetConnection() throws SQLException {
        try (var mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), any(Properties.class)))
                    .thenReturn(connection);

            Connection conn1 = trinoConnectionManager.getConnection("default");
            Connection conn2 = trinoConnectionManager.getConnection("default");

            assertEquals(conn1, conn2);
        }
    }
}
