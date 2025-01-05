package org.vera.amazingFactions.interactions.menus.users;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.vera.amazingFactions.interactions.menus.Menu;
import org.vera.amazingFactions.internal.dto.UserDTO;

import java.time.OffsetDateTime;
import java.util.Set;

public class UserListMenu implements Menu {
    private static String title = ChatColor.RED + "Amazing Factions - Users";
    public static Inventory inventory = Bukkit.createInventory(null, 36, title);

    public UserListMenu(Set<UserDTO> userList) {
        int invSlot = 0;

        for (UserDTO user : userList) {
            Menu.setCustomValues(Material.PLAYER_HEAD, inventory, invSlot, ChatColor.GREEN + user.getUserName(), ChatColor.AQUA + user.getfactionRank());
            invSlot++;
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void handle(Player player, ItemStack clickedItem, InventoryView currentInventory) {

    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public void executeConfirmation(Player player, Menu sourceMenu) {

    }
}
