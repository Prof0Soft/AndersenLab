package by.andersenlab.crudoperation;

import by.andersenlab.poolConnect.pool.impl.PoolConnect;

import java.sql.Connection;

public enum Connect {
    INSTANCE;

    PoolConnect poolConnect;

    Connect() {
        poolConnect = new PoolConnect();
        poolConnect.setDriverClass("org.h2.Driver");
        poolConnect.setJdbcUrl("jdbc:h2:~/test");
        poolConnect.setUser("admin");
        poolConnect.setPassword("123");

        // set options
        poolConnect.setMinPoolSize(5);
        poolConnect.setAcquireIncrement(5);
        poolConnect.setMaxPoolSize(20);
        poolConnect.setMaxStatements(180);
        poolConnect.start();
    }

    public Connection getConnection() {
        return poolConnect.getConnection();
    }

}
