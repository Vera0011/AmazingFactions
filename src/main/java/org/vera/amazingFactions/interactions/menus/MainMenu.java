package org.vera.amazingFactions.interactions.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * Main menu (for the leader of the faction)
 */
public class MainMenu implements Menu {
    private static String title = "Amazing Factions - Main menu";
    public static Inventory inventory = Bukkit.createInventory(null, 9, title);

    static {
        inventory.setItem(0, new ItemStack(Material.DIAMOND_BLOCK, 1));
        inventory.setItem(3, new ItemStack(Material.EMERALD_BLOCK, 1));
        inventory.setItem(8, new ItemStack(Material.REDSTONE_BLOCK, 1));
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void handle(Player player, ItemStack clicked, InventoryView current_inventory) {
        if (clicked.getType() == Material.DIAMOND_BLOCK) {
            return;
        }

        if (clicked.getType() == Material.EMERALD_BLOCK) {
            return;
        }

        if (clicked.getType() == Material.REDSTONE_BLOCK) {
            return;
        }
    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }
}
