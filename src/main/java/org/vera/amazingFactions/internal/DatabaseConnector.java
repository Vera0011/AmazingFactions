package org.vera.amazingFactions.internal;

import org.vera.amazingFactions.AmazingFactions;

import java.io.*;
import java.sql.*;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

/**
 * This class manages the Database connection (SQLite)
 * Uses Singleton pattern to avoid problems
 */
public class DatabaseConnector {
    private static Connection connection;
    private final String databaseName;

    public DatabaseConnector(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * Creates a connection to the database
     */
    public void connect() {
        if (connection == null) {
            File dataFolder = new File("plugins/AmazingFactions/" + databaseName + ".db");

            if (!dataFolder.exists()) {
                dataFolder.getParentFile().mkdirs();
            }

            String url = "jdbc:sqlite:" + dataFolder.getAbsolutePath();
            try {
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                getLogger().severe("Error while connecting to the database: " + e.getMessage());
                getLogger().severe("Amazing Factions could not be loaded.");
                getServer().getPluginManager().disablePlugin(new AmazingFactions());
            }
        }
    }

    /**
     * Returns the actual connection
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Disconnects the connection from the database
     */
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            getLogger().severe("Error closing the database connection: " + e.getMessage());
        }
    }

    /**
     * Executes the database creation (tables, foreign keys...)
     *
     * @return
     */
    public boolean executeInitialization() {
        try {
            Connection conn = getConnection();
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
