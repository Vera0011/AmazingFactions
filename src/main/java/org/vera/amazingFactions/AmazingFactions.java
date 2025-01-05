package org.vera.amazingFactions;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.vera.amazingFactions.interactions.commands.factions.FactionCreate;
import org.vera.amazingFactions.interactions.commands.factions.FactionDelete;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.interactions.commands.factions.FactionMain;
import org.vera.amazingFactions.interactions.commands.factions.FactionUserList;
import org.vera.amazingFactions.interactions.events.InventoryClick;
import org.vera.amazingFactions.interactions.menus.ConfirmationMenu;
import org.vera.amazingFactions.interactions.menus.factions.MainMenu;
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
        Objects.requireNonNull(this.getCommand("amazingfactions-create")).setExecutor(new FactionCreate());
        Objects.requireNonNull(this.getCommand("amazingfactions-delete")).setExecutor(new FactionDelete());
        Objects.requireNonNull(this.getCommand("amazingfactions-user-list")).setExecutor(new FactionUserList());
        Objects.requireNonNull(this.getCommand("factions")).setExecutor(new FactionMain());

        MessageHandler.sendInfoMessage("Commands loaded");
    }

    private void loadEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryClick(), this);

        MessageHandler.sendInfoMessage("Events loaded");
    }

    private void loadMenus() {
        menus = new HashSet<>();

        menus.add(new MainMenu());

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
