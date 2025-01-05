package org.vera.amazingFactions.interactions.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.vera.amazingFactions.AmazingFactions;

public class ConfirmationMenu implements Menu {
    private static String title = ChatColor.RED + "Amazing Factions - Confirmation";
    public static Inventory inventory = Bukkit.createInventory(null, 9, title);
    private Menu parentMenu;

    static {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }

        Menu.setCustomValues(Material.LIME_WOOL, inventory, 3, ChatColor.GREEN + "Confirm action", ChatColor.AQUA + "Confirm and proceed with a specific action", null);
        Menu.setCustomValues(Material.RED_WOOL, inventory, 5, ChatColor.GREEN + "Abort", ChatColor.RED + "Abort action", null);
    }

    public ConfirmationMenu(Menu parent) {
        this.parentMenu = parent;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public void executeConfirmation(Player player, Menu sourceMenu) {
    }

    @Override
    public void handle(Player player, ItemStack clickedItem, InventoryView currentInventory) {
        if (clickedItem.getType() == Material.LIME_WOOL) {
            currentInventory.close();
            this.parentMenu.executeConfirmation(player, this);
            AmazingFactions.menus.remove(this);
            return;
        }

        if (clickedItem.getType() == Material.RED_WOOL) {
            player.closeInventory();
        }
    }
}
