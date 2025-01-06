package org.vera.amazingFactions.interactions.menus;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.vera.amazingFactions.internal.dto.UserDTO;

import javax.annotation.Nullable;
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
    static void setCustomValues(Material material, Inventory currentInventory, int position, String name, ArrayList<String> lore, @Nullable UserDTO user) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }

        if (material == Material.PLAYER_HEAD && user != null) {
            setCustomSkull(item, user);
        }

        if (material == Material.BLACK_BANNER) {
            BannerMeta bannerMeta = (BannerMeta) item.getItemMeta();
            bannerMeta.addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_MIDDLE));
            item.setItemMeta(bannerMeta);
        }

        currentInventory.setItem(position, item);
    }


    // Adds to an inventory a specific item with a custom name and lore
    static void setCustomValues(Material material, Inventory currentInventory, int position, String name, String lore, @Nullable UserDTO user) {
        ArrayList<String> loreList = new ArrayList<>();

        loreList.add(lore);

        setCustomValues(material, currentInventory, position, name, loreList, user);
    }

    // Sets the skin to the player head
    private static void setCustomSkull(ItemStack item, UserDTO user) {
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(user.getUuid());

        if (skullMeta != null) {
            skullMeta.setOwningPlayer(offlinePlayer);
            item.setItemMeta(skullMeta);
        }
    }
}
