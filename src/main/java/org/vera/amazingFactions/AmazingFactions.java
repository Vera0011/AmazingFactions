package org.vera.amazingFactions;

import org.bukkit.plugin.java.JavaPlugin;
import org.vera.amazingFactions.commands.Create;

public final class AmazingFactions extends JavaPlugin {

    @Override
    public void onEnable() {
        this.loadCommands();
        this.loadEvents();

        getLogger().info("Amazing Factions is enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Amazing Factions is disabled");
    }

    private void loadCommands() {
        this.getCommand("create").setExecutor(new Create());
    }

    private void loadEvents() {

    }
}
