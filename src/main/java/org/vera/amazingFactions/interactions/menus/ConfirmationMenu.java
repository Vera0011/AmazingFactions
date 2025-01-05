package org.vera.amazingFactions.interactions.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ConfirmationMenu implements Menu {
    private static String title = ChatColor.RED + "Amazing Factions - Confirmation";
    public static Inventory inventory = Bukkit.createInventory(null, 9, title);
    private Menu parentMenu;

    static {
        Menu.setCustomValues(Material.EMERALD_BLOCK, inventory, 3, ChatColor.GREEN + "Confirm action", ChatColor.AQUA + "Confirm and proceed with a specific action");
        Menu.setCustomValues(Material.REDSTONE_BLOCK, inventory, 4, ChatColor.GREEN + "Abort", ChatColor.RED + "Abort confirmation");
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
        if (clickedItem.getType() == Material.EMERALD_BLOCK) {
            currentInventory.close();
            this.parentMenu.executeConfirmation(player, this);
            return;
        }

        if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
            player.closeInventory();
        }
    }
}
