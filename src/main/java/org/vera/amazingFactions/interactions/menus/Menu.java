package org.vera.amazingFactions.interactions.menus;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public interface Menu {
    String getTitle();

    // Handles interactions (already filtered in the event)
    void handle(Player player, ItemStack clickedItem, InventoryView currentInventory);

    // Opens the menu
    void open(Player player);

    // For confirmation menu: If the option is selected, then the confirmation menu calls his parent (and executes this function)
    void executeConfirmation(Player player, Menu sourceMenu);

    // Adds to an inventory a specific item with a custom name and lore
    static void setCustomValues(Material material, Inventory currentInventory, int position, String name, ArrayList<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }

        /*if (material == Material.PLAYER_HEAD) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(name);
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();

            if (player != null && skullMeta != null) {
                skullMeta.setOwningPlayer(player);
            }
        }*/

        currentInventory.setItem(position, item);
    }


    // Adds to an inventory a specific item with a custom name and lore
    static void setCustomValues(Material material, Inventory currentInventory, int position, String name, String lore) {
        ArrayList<String> loreList = new ArrayList<>();

        loreList.add(lore);

        setCustomValues(material, currentInventory, position, name, loreList);
    }
}
