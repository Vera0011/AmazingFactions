package org.vera.amazingFactions.interactions.menus;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public interface Menu {
    String getTitle();

    void open(Player player);

    void handle(Player player, ItemStack item, InventoryView current_inventory);
}
