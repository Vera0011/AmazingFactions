package org.vera.amazingFactions;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.vera.amazingFactions.interactions.commands.factions.Create;
import org.vera.amazingFactions.interactions.commands.factions.Delete;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.interactions.events.InventoryClick;
import org.vera.amazingFactions.interactions.menus.Main;
import org.vera.amazingFactions.interactions.menus.Menu;
import org.vera.amazingFactions.internal.DatabaseConnector;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class AmazingFactions extends JavaPlugin {
    public static DatabaseConnector database;
    public static Set<Menu> menus;

    @Override
    public void onEnable() {
        boolean result = this.databaseConnect();

        this.loadMenus();
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
        Objects.requireNonNull(this.getCommand("delete")).setExecutor(new Delete());
    }

    private void loadEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryClick(), this);

        MessageHandler.sendInfoMessage("Events loaded");
    }

    private void loadMenus() {
        menus = new HashSet<>();

        menus.add(new Main());

        MessageHandler.sendInfoMessage("Menus loaded");
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
