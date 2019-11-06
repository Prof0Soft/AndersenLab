package by.andersenlab.poolConnect.pool.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class PoolConnectTest {
    private PoolConnect poolConnect = new PoolConnect();

    @BeforeEach
    private void setUp() {
        poolConnect.setDriverClass("org.h2.Driver");
        poolConnect.setJdbcUrl("jdbc:h2:~/test");
        poolConnect.setUser("admin");
        poolConnect.setPassword("123");

        // set options
        poolConnect.setMinPoolSize(1);
        poolConnect.setAcquireIncrement(5);
        poolConnect.setMaxPoolSize(20);
        poolConnect.setMaxStatements(180);
    }

    @Test
    void start() {
        poolConnect.start();

        try (Connection con = poolConnect.getConnection()) {
            Assertions.assertNotNull(con);
            Assertions.assertEquals(poolConnect.getFreeConnectionsSize(), poolConnect.getPoolSize() - 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createFiveConnection() {
        int expected = 5;
        poolConnect.setMinPoolSize(expected);
        poolConnect.start();
        Assertions.assertEquals(expected, poolConnect.getPoolSize());
    }

    @Test
    void close() {
        poolConnect.start();
        poolConnect.close();
        Assertions.assertEquals(0, poolConnect.getFreeConnectionsSize());
        Assertions.assertEquals(0, poolConnect.getPoolSize());
    }
}