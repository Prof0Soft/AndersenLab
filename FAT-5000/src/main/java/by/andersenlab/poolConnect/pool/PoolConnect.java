package by.andersenlab.poolConnect.pool;

import by.andersenlab.poolConnect.DelegateConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface for pool connection
 */
public interface PoolConnect {
    /**
     * Get connection from pool.
     *
     * @return connection from pool.
     */
    Connection getConnection();

    /**
     * Create connetions for pool.
     *
     * @throws SQLException exception for create connection.
     */
    void start() throws SQLException;

    /**
     * The pool's size.
     *
     * @return size.
     */
    int getPoolSize();

    /**
     * Get count free connections in pool.
     *
     * @return count free connections.
     */
    int getFreeConnectionsSize();

    /**
     * Back connection to pool.
     *
     * @param connection the connection.
     */
    void backConnection(DelegateConnection connection);

    /**
     * Close and stop pool connection.
     */
    void close();
}
