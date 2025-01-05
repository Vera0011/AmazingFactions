package org.vera.amazingFactions.interactions.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;

public interface Menu {
    String getTitle();

    void handle(Player player, ItemStack clickedItem, InventoryView currentInventory, @Nullable String source);

    void open(Player player);

    /**
     * Can add to an inventory, a specific item with a custom name and lore
     *
     * @param material
     * @param currentInventory
     * @param position
     * @param name
     * @param lore
     */
    static void setCustomValues(Material material, Inventory currentInventory, int position, String name, ArrayList<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        currentInventory.setItem(position, item);
    }
}
