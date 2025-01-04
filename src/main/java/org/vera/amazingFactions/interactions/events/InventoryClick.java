package org.vera.amazingFactions.interactions.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.interactions.menus.Menu;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        InventoryView current_inventory = (InventoryView) event.getInventory();

        for (Menu menu : AmazingFactions.menus) {
            if (current_inventory.getTitle().equals(menu.getTitle())) {
                menu.handle(player, clicked, current_inventory);
                break;
            }
        }
    }
}
