package org.vera.amazingFactions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.types.Faction;

import java.util.*;

public class Create implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player currentPlayer) {
            if (args.length < 2) {
                currentPlayer.sendMessage(ChatColor.RED + "[AmazingFactions]" + ChatColor.GOLD + " - Usage: /create <name> <description> <list of the users>");
                return true;
            }

            String name = args[0];
            String description = args[1];
            ArrayList<String> users = new ArrayList<>();
            String leader = currentPlayer.getUniqueId().toString();

            // Adds multiple users, or only one (the leader, executor of the command)
            if (args.length > 2) {
                String[] userArgs = Arrays.copyOfRange(args, 2, args.length);
                userArgs[userArgs.length - 1] = leader;

                // Convert the array to a HashSet to remove duplicates
                Set<String> usersInput = new HashSet<>(Arrays.asList(userArgs));

                for (String user : usersInput) {
                    Player validUser = Bukkit.getPlayer(user);

                    if (validUser != null) {
                        users.add(validUser.getUniqueId().toString());
                    }
                }
            } else {
                users.add(leader);
            }

            new Faction(name, description, users, leader);

            currentPlayer.sendMessage(ChatColor.RED + "[AmazingFactions]" + ChatColor.GOLD + " - You created a new faction." +
                    ChatColor.AQUA + "\n|| -->  Name: " + ChatColor.GREEN + name +
                    ChatColor.AQUA + "\n|| -->  Description: " + ChatColor.GREEN + description +
                    ChatColor.AQUA + "\n|| -->  Leader: " + ChatColor.GREEN + currentPlayer.getName() +
                    ChatColor.AQUA + "\n|| -->  Number of users: " + ChatColor.GREEN + users.size());

            return true;
        }

        System.out.println("This command can only be executed by a player");
        return true;
    }
}