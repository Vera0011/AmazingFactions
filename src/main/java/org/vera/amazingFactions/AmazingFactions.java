package org.vera.amazingFactions;

import org.bukkit.plugin.java.JavaPlugin;
import org.vera.amazingFactions.commands.Create;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.internal.DatabaseConnector;

import java.util.Objects;

public final class AmazingFactions extends JavaPlugin {
    public static DatabaseConnector database;

    @Override
    public void onEnable() {
        boolean result = this.databaseConnect();

        this.loadCommands();
        this.loadEvents();

        if (result) MessageHandler.sendInfoMessage("Amazing Factions is enabled.");
    }

    @Override
    public void onDisable() {
        this.databaseDisconnect();
        MessageHandler.sendInfoMessage("Amazing Factions is disabled");
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
        database.connect();
        return database.executeInitialization();
    }

    /**
     * Method to stop a database connection
     */
    private void databaseDisconnect() {
        database.disconnect();
    }
}
