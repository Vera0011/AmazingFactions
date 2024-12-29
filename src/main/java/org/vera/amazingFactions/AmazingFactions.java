package org.vera.amazingFactions;

import org.bukkit.plugin.java.JavaPlugin;

public final class AmazingFactions extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Amazing Factions is enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Amazing Factions is disabled");
    }
}
