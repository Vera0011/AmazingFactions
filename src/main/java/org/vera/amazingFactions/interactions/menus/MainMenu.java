package org.vera.amazingFactions.interactions.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.vera.amazingFactions.AmazingFactions;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * Main menu (for the leader of the faction)
 */
public class MainMenu implements Menu {
    private static String title = ChatColor.RED + "Amazing Factions - Main menu";
    public static Inventory inventory = Bukkit.createInventory(null, 36, title);

    static {
        ArrayList<String> customLoreDiamondBlock = new ArrayList<>();
        ArrayList<String> customLoreEmeraldBlock = new ArrayList<>();
        ArrayList<String> customLoreRedstoneBlock = new ArrayList<>();
        ArrayList<String> customLoreNetherStar = new ArrayList<>();

        customLoreEmeraldBlock.add(ChatColor.AQUA + "Display members of the current faction");
        customLoreNetherStar.add(ChatColor.AQUA + "Add a new user to the faction");
        customLoreDiamondBlock.add(ChatColor.AQUA + "Display stats of the current faction");
        customLoreRedstoneBlock.add(ChatColor.RED + "This action will remove permanently the faction");

        Menu.setCustomValues(Material.EMERALD_BLOCK, inventory, 0, ChatColor.GREEN + "Display users", customLoreEmeraldBlock);
        //Menu.setCustomValues(Material.NETHER_STAR, inventory, 9, ChatColor.GREEN + "Add new user", customLoreNetherStar);
        //Menu.setCustomValues(Material.DIAMOND_BLOCK, inventory, 0, ChatColor.GREEN + "Display stats", customLoreDiamondBlock);
        Menu.setCustomValues(Material.REDSTONE_BLOCK, inventory, 35, ChatColor.GREEN + "Remove faction", customLoreRedstoneBlock);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void handle(Player player, ItemStack clicked, InventoryView currentInventory, @Nullable String source) {
        if (clicked.getType() == Material.DIAMOND_BLOCK) {
            return;
        }

        /*if (clicked.getType() == Material.EMERALD_BLOCK) {
            return;
        }*/

        if (clicked.getType() == Material.REDSTONE_BLOCK) {
            player.performCommand("amazingfactions-delete");
            currentInventory.close();
        }
    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }
}
