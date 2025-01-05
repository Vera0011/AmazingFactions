package org.vera.amazingFactions.interactions.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ConfirmationMenu implements Menu {
    private static String title = ChatColor.RED + "Amazing Factions - Confirmation";
    public static Inventory inventory = Bukkit.createInventory(null, 9, title);

    static {
        ArrayList<String> customLoreEmeraldBlock = new ArrayList<>();
        ArrayList<String> customLoreRedstoneBlock = new ArrayList<>();

        customLoreEmeraldBlock.add(ChatColor.AQUA + "Confirm and proceed with a specific action");
        customLoreRedstoneBlock.add(ChatColor.RED + "Abort confirmation");

        Menu.setCustomValues(Material.EMERALD_BLOCK, inventory, 3, ChatColor.GREEN + "Confirm action", customLoreEmeraldBlock);
        Menu.setCustomValues(Material.REDSTONE_BLOCK, inventory, 4, ChatColor.GREEN + "Abort", customLoreRedstoneBlock);
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
    public void handle(Player player, ItemStack clickedItem, InventoryView currentInventory, @Nullable String source) {
        if (clickedItem.getType() == Material.EMERALD_BLOCK) {
            return;
        }

        if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
            player.closeInventory();
        }
    }
}
