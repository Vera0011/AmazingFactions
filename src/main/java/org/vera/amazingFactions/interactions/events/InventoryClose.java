package org.vera.amazingFactions.interactions.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.interactions.menus.Menu;


public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView currentInventory = event.getView();

        for (Menu menu : AmazingFactions.menus) {
            if (currentInventory.getTitle().equals(menu.getTitle())) {
                AmazingFactions.menus.remove(menu);
                break;
            }
        }
    }
}
