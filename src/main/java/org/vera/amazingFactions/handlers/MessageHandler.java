package org.vera.amazingFactions.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class MessageHandler {
    private static final String pluginConsolerefix = "[AmazingFactions] - ";
    private static final String pluginPlayerPrefix = ChatColor.RED + "[AmazingFactions] - ";

    public static void sendErrorMessage(Player player, String message) {
        player.sendMessage(pluginPlayerPrefix + ChatColor.DARK_RED + message + ChatColor.RESET);
    }

    public static void sendErrorMessage(String message) {
        getLogger().severe(pluginConsolerefix + message);
    }

    public static void sendWarningMessage(Player player, String message) {
        player.sendMessage(pluginPlayerPrefix + ChatColor.YELLOW + message + ChatColor.RESET);
    }

    public static void sendWarningMessage(String message) {
        getLogger().warning(pluginConsolerefix + message);
    }

    public static void sendInfoMessage(Player player, String message) {
        player.sendMessage(pluginPlayerPrefix + ChatColor.GREEN + message + ChatColor.RESET);
    }

    public static void sendInfoMessage(String message) {
        getLogger().info(pluginConsolerefix + message);
    }
}
