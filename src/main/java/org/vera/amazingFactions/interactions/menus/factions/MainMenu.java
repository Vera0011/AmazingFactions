package org.vera.amazingFactions.interactions.menus.factions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.interactions.menus.ConfirmationMenu;
import org.vera.amazingFactions.interactions.menus.Menu;
import org.vera.amazingFactions.internal.dto.UserDTO;

/**
 * Main menu (for the leader of the faction)
 */
public class MainMenu implements Menu {
    private static String title = ChatColor.RED + "Amazing Factions - Main menu";
    public static Inventory inventory = Bukkit.createInventory(null, 36, title);

    static {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (i <= 9 || i >= 26 && i <= 35 || i == 17 || i == 18) {
                inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }
        }

        Menu.setCustomValues(Material.WRITABLE_BOOK, inventory, 12, ChatColor.GREEN + "Add New User", ChatColor.AQUA + "Add a new user to the faction", null);
        Menu.setCustomValues(Material.BARRIER, inventory, 21, ChatColor.GREEN + "Remove User", ChatColor.RED + "This action will permanently remove a user from your faction", null);
        Menu.setCustomValues(Material.BLACK_BANNER, inventory, 23, ChatColor.GREEN + "Display factions", ChatColor.RED + "Display all the factions of the server", null);
        Menu.setCustomValues(Material.BOOK, inventory, 14, ChatColor.GREEN + "Display Stats", ChatColor.AQUA + "Display stats of the current faction", null);
        Menu.setCustomValues(Material.TNT, inventory, 25, ChatColor.GREEN + "Remove Faction", ChatColor.RED + "This action will permanently remove the faction", null);
    }

    public MainMenu(UserDTO currentUser) {
        Menu.setCustomValues(Material.PLAYER_HEAD, inventory, 10, ChatColor.GREEN + "Display Users", ChatColor.AQUA + "Display members of the current faction", currentUser);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void handle(Player player, ItemStack clicked, InventoryView currentInventory) {
        if (clicked.getType() == Material.PLAYER_HEAD) {
            player.performCommand("amazingfactions-user-list");
            return;
        }

        if (clicked.getType() == Material.WRITABLE_BOOK) {
            return;
        }

        if (clicked.getType() == Material.BARRIER) {
            return;
        }

        if (clicked.getType() == Material.BOOK) {
            return;
        }

        if (clicked.getType() == Material.BLACK_BANNER) {
            player.performCommand("amazingfactions-list");
            return;
        }

        if (clicked.getType() == Material.TNT) {
            ConfirmationMenu confMenu = new ConfirmationMenu(this);
            AmazingFactions.menus.add(confMenu);

            confMenu.open(player);
        }
    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }

    public void executeConfirmation(Player player, Menu sourceMenu) {
        player.performCommand("amazingfactions-delete");
        AmazingFactions.menus.remove(sourceMenu);
    }
}
