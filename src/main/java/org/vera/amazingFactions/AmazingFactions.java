package org.vera.amazingFactions;

import org.bukkit.plugin.java.JavaPlugin;
import org.vera.amazingFactions.commands.Create;
import org.vera.amazingFactions.internal.DatabaseConnector;

import java.sql.SQLException;
import java.util.Objects;

public final class AmazingFactions extends JavaPlugin {
    DatabaseConnector database;

    @Override
    public void onEnable() {
        this.loadCommands();
        this.loadEvents();
        boolean result = this.databaseConnect();

        if (result) getLogger().info("Amazing Factions is enabled.");
    }

    @Override
    public void onDisable() {
        this.databaseDisconnect();
        getLogger().info("Amazing Factions is disabled");
    }

    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("create")).setExecutor(new Create());
    }

    private void loadEvents() {

    }

    /**
     * Method to start a database connection
     *
     * @return true if the connection was successfully
     */
    private boolean databaseConnect() {
        database = new DatabaseConnector("AmazingFactions");

        try {
            database.connect();

            return database.executeInitialization();
        } catch (SQLException e) {
            getLogger().severe("Error while connecting to the database: " + e.getMessage());
            getLogger().severe("Amazing Factions could not be loaded.");
            getServer().getPluginManager().disablePlugin(this);

            return false;
        }
    }

    /**
     * Method to stop a database connection
     */
    private void databaseDisconnect() {
        try {
            if (database != null) {
                database.disconnect();
            }
        } catch (SQLException e) {
            getLogger().severe("Error closing the database connection: " + e.getMessage());
        }
    }
}
