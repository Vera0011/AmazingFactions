package org.vera.amazingFactions.internal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

/**
 * This class manages the Database connection (SQLite)
 */
public class DatabaseConnector {
    private Connection connection;
    private final String databaseName;

    public DatabaseConnector(String databaseName) {
        this.databaseName = databaseName;
    }

    public void connect() throws SQLException {
        File dataFolder = new File("plugins/AmazingFactions", databaseName + ".db");

        if (!dataFolder.exists()) {
            dataFolder.getParentFile().mkdirs();
        }

        String url = "jdbc:sqlite:" + dataFolder.getAbsolutePath();
        connection = DriverManager.getConnection(url);
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
