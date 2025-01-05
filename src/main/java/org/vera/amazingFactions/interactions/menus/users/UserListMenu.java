package org.vera.amazingFactions.interactions.menus.users;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.interactions.menus.Menu;
import org.vera.amazingFactions.internal.dto.UserDTO;

import java.util.Set;

public class UserListMenu implements Menu {
    private static String title = ChatColor.RED + "Amazing Factions - Users";
    public static Inventory inventory = Bukkit.createInventory(null, 36, title);

    public UserListMenu(Set<UserDTO> userList) {
        int invSlot = 10;

        for (int i = 0; i < inventory.getSize(); i++) {
            if (i <= 9 || i >= 26 && i <= 35 || i == 17 || i == 18) {
                inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }
        }

        for (UserDTO user : userList) {
            if (invSlot > 9 || invSlot < 26 && invSlot > 35 || invSlot != 17 || invSlot != 18) {
                Menu.setCustomValues(Material.PLAYER_HEAD, inventory, invSlot, ChatColor.GREEN + user.getUserName(), ChatColor.AQUA + user.getfactionRank(), user);
                invSlot++;
            }
        }

        Menu.setCustomValues(Material.RED_WOOL, inventory, 35, ChatColor.GREEN + "Exit", ChatColor.RED + "Close this menu", null);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void handle(Player player, ItemStack clickedItem, InventoryView currentInventory) {
        if (clickedItem.getType() == Material.RED_WOOL) {
            player.closeInventory();
            AmazingFactions.menus.remove(this);
        }

    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public void executeConfirmation(Player player, Menu sourceMenu) {

    }
}
