package org.vera.amazingFactions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.types.Faction;

import java.util.ArrayList;
import java.util.Arrays;

public class Create implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player currentPlayer) {
            if (args.length < 1) {
                return false;
            }

            String name = args[0];
            String description = args[1];
            ArrayList<String> users = new ArrayList<>();
            String leader = currentPlayer.getUniqueId().toString();

            if (args.length > 2) {
                users = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(args, 2, args.length)));
            }

            users.add(leader);

            new Faction(name, description, users, leader);

            currentPlayer.sendMessage(ChatColor.RED + "[AmazingFactions]" + ChatColor.GOLD + " You created a new faction." +
                    ChatColor.AQUA + "\n|| -->  Name: " + ChatColor.GREEN + name +
                    ChatColor.AQUA + "\n|| -->  Description: " + ChatColor.GREEN + description +
                    ChatColor.AQUA + "\n|| -->  Leader: " + ChatColor.GREEN + currentPlayer.getName() +
                    ChatColor.AQUA + "\n|| -->  Number of users: " + ChatColor.GREEN + users.size() + ChatColor.AQUA);

            return true;
        }

        System.out.println("This command can only be executed by a player");
        return true;
    }
}