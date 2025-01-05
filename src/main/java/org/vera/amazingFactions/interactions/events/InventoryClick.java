package org.vera.amazingFactions.interactions.events;

import org.bukkit.Material;
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
        InventoryView currentInventory = event.getView();

        if (clicked == null || clicked.getType() == Material.AIR) {
            return;
        }

        for (Menu menu : AmazingFactions.menus) {
            if (currentInventory.getTitle().equals(menu.getTitle())) {
                event.setCancelled(true);
                menu.handle(player, clicked, currentInventory, null);
                break;
            }
        }
    }
}
