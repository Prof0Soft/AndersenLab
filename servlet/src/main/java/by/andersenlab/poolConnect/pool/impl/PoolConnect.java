package by.andersenlab.poolConnect.pool.impl;

import by.andersenlab.poolConnect.DelegateConnection;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Class for create data pool connect.
 * How to use:
 * 1. Implements the class.
 * 2. Set settings for connect and pool's size.
 * 3. Run method start of implemented class.
 */
@Data
public class PoolConnect implements by.andersenlab.poolConnect.pool.PoolConnect {
    private static Logger logger = LogManager.getLogger(PoolConnect.class);
    private Connection delegateConnection;

    // Data base connection
    private String driverClass;
    private String jdbcUrl;
    private String user;
    private String password;

    // Settings for pool.
    private int minPoolSize = 3;
    private int acquireIncrement = 5;
    private int maxPoolSize = 10;
    private int maxStatements = 50;

    // properties
    private int timeForWhiteFreeConnections = 1000;
    private int size = 0;
    //pool
    Queue<DelegateConnection> poolCon = null;

    @Override
    public void start() {
        // first init pool connection
        if (poolCon == null) {
            poolCon = new ArrayDeque<>(minPoolSize);
            for (int i = 0; i < minPoolSize; i++) {
                poolCon.add(createConnection());
                size++;
            }
        }
        logger.info("Created " + poolCon.size() + " connections");
    }

    @Override
    public int getPoolSize() {
        return size;
    }

    @Override
    public int getFreeConnectionsSize() {
        return getCountFreeConnections().intValue();
    }

    @Override
    public void backConnection(DelegateConnection connection) {
        connection.setIsFree(true);
        poolCon.add(connection);
        logger.info("Connection was back: " + connection);
    }

    @Override
    public void close() {
        poolCon.forEach(n -> {
            try {
                n.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        poolCon.clear();
        size = 0;
        logger.info("Stop pool connection");
    }

    /**
     * Create single connection for pool.
     *
     * @return connection in delegate class DelegateConnection
     */
    private DelegateConnection createConnection() {
        try {
            return new DelegateConnection(DriverManager.getConnection(jdbcUrl, user, password), this);
        } catch (SQLException e) {
            logger.error("Can't add more connections", e);
            return null;
        }
    }

    @Override
    public Connection getConnection() {
        if (getCountFreeConnections() <= 0) {
            while (!getMoreConnection()) {
                try {
                    Thread.sleep(timeForWhiteFreeConnections);
                    logger.warn("End free and maxStatement of pool. MaxStatement is  " + maxStatements);
                } catch (InterruptedException e) {
                    logger.error("End free and maxStatement of pool", e);
                }
            }
        }
        DelegateConnection con = poolCon.poll();
        con.setIsFree(false);
        return con;
    }

    /**
     * Create more connections if need.
     *
     * @return true if connections was create, and false if connections wasn't create.
     */
    private boolean getMoreConnection() {
        if (acquireIncrement + poolCon.size() > maxStatements) {
            return false;
        }

        for (int i = 0; i < acquireIncrement; i++) {
            poolCon.add(createConnection());
            size++;
        }
        return true;
    }

    /**
     * Get count of free connections.
     *
     * @return
     */
    private Long getCountFreeConnections() {
        return poolCon.stream()
                .filter(DelegateConnection::getIsFree)
                .count();
    }
}
