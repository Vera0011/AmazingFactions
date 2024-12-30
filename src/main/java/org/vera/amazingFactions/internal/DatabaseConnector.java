package org.vera.amazingFactions.internal;

import java.io.*;
import java.sql.*;

import static org.bukkit.Bukkit.getLogger;

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
        File dataFolder = new File("plugins/AmazingFactions/" + databaseName + ".db");

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

    /**
     * Executes the database creation (tables, foreign keys...)
     * @return
     */
    public boolean executeInitialization() {
        try (Connection conn = this.connection) {
            Statement stmt = conn.createStatement();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/initialization.sql");
            try {
                if (inputStream == null) {
                    System.out.println("SQL File not found");
                    return false;
                }

                try (BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    StringBuilder sb = new StringBuilder();

                    while ((line = buff.readLine()) != null) {
                        sb.append(line);

                        if (line.contains(";")) {
                            stmt.execute(sb.toString());
                            sb.setLength(0);
                        }
                    }

                }
            } catch (IOException e) {
                getLogger().severe("IO EXCEPTION: " + e.getMessage());
                return false;
            }

            return true;
        } catch (SQLException e) {
            getLogger().severe("SQL EXCEPTION: " + e.getMessage());
            return false;
        }
    }
}
