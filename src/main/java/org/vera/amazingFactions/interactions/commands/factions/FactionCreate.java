package org.vera.amazingFactions.interactions.commands.factions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.internal.dto.FactionDTO;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.internal.services.FactionService;

import java.util.*;

public class FactionCreate implements CommandExecutor {
    private static FactionService factionService = new FactionService();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player currentPlayer = (Player) sender;

            if (args.length < 2) {
                MessageHandler.sendInfoMessage(currentPlayer, "Usage: /create <faction name> <faction description> <list of the users>");
                return true;
            }

            String name = args[0];
            String description = args[1];
            Set<UUID> users = new HashSet<>();
            UUID leaderUUID = currentPlayer.getUniqueId();

            // Adds multiple users, or only one (the leader, executor of the command)
            if (args.length > 2) {
                String leader = currentPlayer.getName();
                String[] userArgs = Arrays.copyOfRange(args, 2, args.length);
                userArgs[userArgs.length - 1] = leader;

                // Convert the array to a HashSet to remove duplicates
                Set<String> usersInput = new HashSet<>(Arrays.asList(userArgs));

                for (String user : usersInput) {
                    Player validUser = Bukkit.getPlayer(user);

                    if (validUser != null) {
                        users.add(validUser.getUniqueId());
                    }
                }
            } else {
                users.add(leaderUUID);
            }

            FactionDTO createdFaction = factionService.createFaction(currentPlayer, name, description, users, leaderUUID);

            if (createdFaction != null) {
                MessageHandler.sendInfoMessage(currentPlayer, "A new faction was created" +
                        ChatColor.AQUA + "\n|| -->  Name: " + ChatColor.GREEN + name +
                        ChatColor.AQUA + "\n|| -->  Description: " + ChatColor.GREEN + description +
                        ChatColor.AQUA + "\n|| -->  Leader: " + ChatColor.GREEN + currentPlayer.getName() +
                        ChatColor.AQUA + "\n|| -->  Number of users: " + ChatColor.GREEN + users.size());
            }

            return true;
        }

        MessageHandler.sendErrorMessage("This command can only be used by players");
        return true;
    }
}